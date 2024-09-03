package org.example.respositories;

import org.example.models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {
    private Map<Long, Gate> gates = new TreeMap<>();
    private long lastGateId = 0;

    public Optional<Gate> findGateById(long id) {
        return gates.values().stream().filter(g -> g.getId() == id).findFirst();
    }

    public Gate save(Gate gate) {
        if(gate.getId() == null){
            lastGateId++;
            gate.setId(lastGateId);
            gates.put(lastGateId, gate);
        }else{
            gates.put(gate.getId(), gate);
        }
        return gate;
    }
}
