package net.suttonbm.aoc2024.day10.service;

import net.suttonbm.aoc2024.day10.model.TopoMap;
import net.suttonbm.aoc2024.day10.model.Trail;
import net.suttonbm.aoc2024.day10.model.TrailBuilder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrailFinder {
    public List<Trail> search(TopoMap map) {
        List<Trail> trails = new ArrayList<>();

        for (int i=0; i<map.rows(); i++) {
            for (int j=0; j<map.cols(); j++) {
                Trail trail = TrailBuilder.build(map, new Point(i, j))
                        .orElse(null);
                if (trail != null) {
                    trails.add(trail);
                }
            }
        }

        return trails;
    }
}
