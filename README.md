# ConcurrentCache
A concurrent cache that can be used by multiple threads to save and access. 
It has various eviction strategies and provisions to add more as and when required. 
Modular design and can be extended to as much extent possible. 
ConcurrentHashMap and ConcurrentLinkedDeque are used as internal data structures, 
  FixThreadPool is used for the eviction strategies to run.  
