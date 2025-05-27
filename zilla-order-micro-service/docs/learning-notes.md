## 🧠 Technical Notes

### ✅ InventoryService
- On the inventory Service I used `Map<String, Inventory>` to transform the list that came from the bank on a lookupTable.
- This avoids nested loops (O(n^2)) and is more efficient  O(1) when iterating trough the table.