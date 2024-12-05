package net.suttonbm.aoc2024.day5.service;

import net.suttonbm.aoc2024.day5.model.Constraint;
import net.suttonbm.aoc2024.day5.model.ProposedUpdate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidationService {

    /**
     * Validates proposed updates based on the provided constraints.
     * @param constraints The list of constraints.
     * @param proposedUpdates The list of proposed updates.
     * @return A list of valid proposed updates.
     */
    public List<ProposedUpdate> validateProposedUpdates(List<Constraint> constraints, List<ProposedUpdate> proposedUpdates) {
        List<ProposedUpdate> validUpdates = new ArrayList<>();

        for (ProposedUpdate update : proposedUpdates) {
            if (isValid(update, constraints)) {
                validUpdates.add(update);
            }
        }

        return validUpdates;
    }

    /**
     * Checks if a proposed update is valid based on all constraints.
     * @param proposedUpdate The proposed update to check.
     * @param constraints The list of constraints to validate against.
     * @return true if the proposed update is valid, false otherwise.
     */
    private boolean isValid(ProposedUpdate proposedUpdate, List<Constraint> constraints) {
        List<Integer> updateNumbers = proposedUpdate.getNumbers();

        for (Constraint constraint : constraints) {
            int first = constraint.getFirst();
            int second = constraint.getSecond();

            if (updateNumbers.contains(first) && updateNumbers.contains(second)) {
                // Check if first appears before second in the list
                if (updateNumbers.indexOf(first) >= updateNumbers.indexOf(second)) {
                    return false; // Invalid because order is not respected
                }
            } else if (updateNumbers.contains(first) || updateNumbers.contains(second)) {
                // If one of the numbers is in the update but not both, it's valid
                continue;
            } else {
                // If neither of the constraint numbers are in the proposed update, it's valid
                continue;
            }
        }

        return true; // If all constraints are satisfied
    }

    /**
     * Extracts the middle element of a valid proposed update.
     * @param update The valid proposed update.
     * @return The middle element from the update list.
     */
    public int extractMiddleElement(ProposedUpdate update) {
        List<Integer> updateNumbers = update.getNumbers();
        int middleIndex = updateNumbers.size() / 2;
        return updateNumbers.get(middleIndex);
    }

    /**
     * Calculates the sum of middle elements from a list of valid proposed updates.
     * @param validUpdates The list of valid proposed updates.
     * @return The sum of the middle elements.
     */
    public int calculateSumOfMiddleElements(List<ProposedUpdate> validUpdates) {
        int sum = 0;

        for (ProposedUpdate update : validUpdates) {
            sum += extractMiddleElement(update);
        }

        return sum;
    }

    /**
     * Corrects invalid proposed updates by attempting to reorder the elements to satisfy relevant constraints.
     * @param constraints The list of global constraints.
     * @param invalidUpdates The list of invalid proposed updates.
     * @return A list of corrected proposed updates.
     */
    public List<ProposedUpdate> correctInvalidUpdates(List<Constraint> constraints, List<ProposedUpdate> invalidUpdates) {
        List<ProposedUpdate> correctedUpdates = new ArrayList<>();

        for (ProposedUpdate update : invalidUpdates) {
            List<Integer> updateNumbers = new ArrayList<>(update.getNumbers());

            // Step 1: Find the relevant constraints for this specific update
            List<Constraint> relevantConstraints = findRelevantConstraints(constraints, updateNumbers);

            // Step 2: Try to reorder the invalid update based on the topologically sorted relevant constraints
            List<Integer> correctedUpdate = reorderUpdate(updateNumbers, relevantConstraints);

            correctedUpdates.add(new ProposedUpdate(correctedUpdate));
        }

        return correctedUpdates;
    }

    /**
     * Finds the constraints that involve numbers present in the given proposed update.
     * @param constraints The list of all constraints.
     * @param updateNumbers The numbers in the proposed update.
     * @return A list of constraints that involve numbers in the update.
     */
    private List<Constraint> findRelevantConstraints(List<Constraint> constraints, List<Integer> updateNumbers) {
        List<Constraint> relevantConstraints = new ArrayList<>();

        for (Constraint constraint : constraints) {
            if (updateNumbers.contains(constraint.getFirst()) && updateNumbers.contains(constraint.getSecond())) {
                relevantConstraints.add(constraint);
            }
        }

        return relevantConstraints;
    }

    /**
     * Reorders the invalid proposed update based on the relevant constraints using topological sorting.
     * @param updateNumbers The numbers in the invalid proposed update.
     * @param relevantConstraints The relevant constraints for this update.
     * @return The reordered proposed update.
     */
    private List<Integer> reorderUpdate(List<Integer> updateNumbers, List<Constraint> relevantConstraints) {
        // Step 1: Build the directed graph for the relevant constraints
        Map<Integer, List<Integer>> graph = buildGraph(relevantConstraints);

        // Step 2: Perform topological sort on the graph
        List<Integer> sortedOrder = topologicalSort(graph);

        // Step 3: Reorder the updateNumbers based on the sorted order
        return reorderNumbers(updateNumbers, sortedOrder);
    }

    /**
     * Builds a directed graph from the given list of constraints.
     * @param constraints The list of constraints.
     * @return A map representing the directed graph.
     */
    private Map<Integer, List<Integer>> buildGraph(List<Constraint> constraints) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (Constraint constraint : constraints) {
            graph.putIfAbsent(constraint.getFirst(), new ArrayList<>());
            graph.putIfAbsent(constraint.getSecond(), new ArrayList<>());
            graph.get(constraint.getFirst()).add(constraint.getSecond());
        }

        return graph;
    }

    /**
     * Performs topological sort on the graph to get the valid order.
     * @param graph The directed graph.
     * @return A list of integers representing the topologically sorted order.
     */
    private List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> sortedOrder = new ArrayList<>();

        // Initialize in-degree for each node
        for (Integer node : graph.keySet()) {
            inDegree.put(node, 0);
        }

        // Calculate in-degree for each node
        for (List<Integer> neighbors : graph.values()) {
            for (Integer neighbor : neighbors) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        // Add nodes with 0 in-degree to the queue
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        // Perform topological sort using Kahn's algorithm
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            sortedOrder.add(node);

            for (Integer neighbor : graph.get(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If the sortedOrder size is less than the number of nodes, there was a cycle
        if (sortedOrder.size() != graph.size()) {
            throw new IllegalStateException("Cycle detected in the constraints graph");
        }

        return sortedOrder;
    }

    /**
     * Reorders the numbers of the proposed update based on the topologically sorted order.
     * @param updateNumbers The numbers in the invalid proposed update.
     * @param sortedOrder The topologically sorted order.
     * @return The reordered list of numbers.
     */
    private List<Integer> reorderNumbers(List<Integer> updateNumbers, List<Integer> sortedOrder) {
        // Map the sorted order to their indices
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < sortedOrder.size(); i++) {
            indexMap.put(sortedOrder.get(i), i);
        }

        // Sort the update numbers based on the sorted order indices
        updateNumbers.sort(Comparator.comparingInt(indexMap::get));

        return updateNumbers;
    }
}