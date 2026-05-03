# Capstone Project: Smart Scheduler
## Option B — Priority Queues & Heaps

---

## What I'm Building

An Emergency Room Triage System. The problem is pretty straightforward, patients come in constantly and they're not all equally urgent. Someone having a cardiac arrest can't wait behind someone with a sprained ankle just because they got there first. A regular FIFO queue completely fails here. I'm using a Max-Heap Priority Queue so the most critical patient always gets seen first, no matter the order they arrived.

This same pattern shows up everywhere — OS scheduling, airline boarding, loan processing. I picked the ER context because the stakes make it obvious why priority actually matters.

---

## Why a Max-Heap

I looked at a few options:

| Structure | insert | removeMax | Notes |
|---|---|---|---|
| Unsorted LinkedList | O(1) | O(n) | Too slow when the list gets big |
| Sorted LinkedList | O(n) | O(1) | Every insert becomes expensive |
| **Max-Heap (ArrayList)** | **O(log n)** | **O(log n)** | Best of both |
| Sorted Array | O(n) | O(1) | Shifting elements kills performance |

The heap wins because both operations stay at O(log n) regardless of how many patients are in the system. Backing it with an ArrayList instead of a linked structure because index access is O(1) and the memory layout is cleaner. 
The tradeoff I'm accepting: you can't iterate patients in priority order without doing repeated removeMax calls. 
---

## Big-O Expectations

| Operation | Complexity | Why |
|---|---|---|
| `admit(patient)` | O(log n) | Insert at end, upheap at most tree height = floor(log n) |
| `treatNext()` | O(log n) | Swap root with last, downheap at most floor(log n) |
| `currentCritical()` | O(1) | Root is always the max, just heap.get(0) |
| Build n patients from scratch | O(n log n) | n inserts, each O(log n) |

The reason O(log n) is such a big deal here: for 500 patients in the queue, an unsorted list's removeMax costs 500 comparisons. The heap costs log2(500) which is about 9. That difference gets bigger as the system scales.

Also worth noting, unlike a BST, a heap can't degenerate. The Complete Binary Tree Property guarantees height is always floor(log n) because the tree fills level by level, left to right. There's no bad insertion order to worry about.

---
