## 🧠 Technical Notes

### ✅ Algorithms
- On the inventory Service: I used `Map<String, Inventory>` to transform the list that came from the bank on a <strong>lookupTable LUT</strong>.
This avoids nested loops (O(n^2)) and is more efficient  O(1) when iterating trough the table.
  <br><br>
- On the Order Service: When an order is about to complete I check if all the products are available on the inventory service. 
Instead of rolling back the entire order if some items are missing I decided to check which ones are available. 
in this case I decided to split the list in two parts: the ones in stock and the ones out. For that case I used the <strong>Partitioning Algorithm </strong>.
<br>To make even better: I reduced to complexity by extracting a set with only the available, going from  O(n × m) to O(n + m). If I had to iterate on the entire list with equals it would be n*m  