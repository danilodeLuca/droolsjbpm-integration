package org.jbpm.simulation.impl;

import org.drools.runtime.process.NodeInstance;
import org.jbpm.simulation.ActivitySimulator;
import org.jbpm.simulation.SimulationContext;
import org.jbpm.simulation.SimulationEvent;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.node.EndNodeInstance;

public class SimulationEndNodeInstance extends EndNodeInstance {

    private static final long serialVersionUID = 4148987012107271001L;

    @Override
    public void internalTrigger(NodeInstance from, String type) {
        System.out.println("Triggered " + getNode().getName() + " id " + getNode().getMetaData().get("UniqueId"));
        SimulationContext context = SimulationContext.getContext();
        
        ActivitySimulator simulator = context.getRegistry().getSimulator(getNode());
        SimulationEvent event = simulator.simulate(this, context);
        
        context.getRepository().storeEvent(event);
        ((NodeInstanceContainer) getNodeInstanceContainer()).nodeInstanceCompleted(this, null);
    }

    
}
