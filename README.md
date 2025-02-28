## Proiect Vertex Cover
# Autori: Berescu Silvia-Maria și Sandu Elena Alexandra

Acest proiect conține implementări pentru evaluarea și benchmarking-ul algoritmilor care rezolvă problema vertex cover. 
Mai jos este detaliată structura arhivei, instrucțiuni pentru evaluarea soluțiilor și sursele externe utilizate.

Structura

- pom.xml: fișierul Maven Project Object Model, care definește dependențele și configurația proiectului pentru compilare și rulare.
- evaluate.ipynb: un notebook Jupyter utilizat pentru analiza datelor și vizualizarea graficelor.
- benchmark_plot_chart_edges.py și benchmark_plot_chart_nodes.py: scripturi Python pentru generarea de grafice și vizualizarea 
rezultatelor benchmark-urilor pe baza muchiilor și nodurilor.

Fișiere CSV

- Benchmark_Results_VertexCoverBinarySearch_Clean.csv: Rezultate benchmark pentru analiza algoritmului pe baza de căutare binară 
pentru vertex cover.
- Benchmark_VertexCoverTree.csv: Date privind benchmark-ul algoritmului vertex cover pentru structuri de tip arbore.
- Graph_Test_Data.csv: Date de intrare utilizate pentru testarea algoritmilor pe grafuri. Număr de noduri și muchii utilizate 
pentru fiecare test, pe baza cărora au fost create graficele. 
- HeuristicBenchmark.csv: Date benchmark specifice algoritmului euristic.

/grafice
- Conține imagini PNG generate cu graficele generate prin scripturile pyhton.

/src/main/java/org
algorithms
- Include implementarea algoritmilor care rezolvă probelma minimum vertex cover, o clasă Main care configurează framework-ul JMH 
pentru a rula benchmark-ul definit în VertexCoverBenchmark.

/benchmarks
- Clasa VertexCoverBenchmark este utilizată pentru a evalua performanța diverșilor algoritmi de acoperire a vârfurilor, folosind
framework-ul JMH. Aceasta definește metode pentru configurarea testelor, execuția benchmark-urilor și colectarea metricilor legate de timp. 

/output
- Conține rezultate generate din rulările algoritmilor.

/tests
- Include teste pentru verificarea corectitudinii algoritmilor, generate cu ajutorul lui ChatGPT, un model de limbaj dezvoltat de OpenAI.

Pentru a evalua algoritmii implementați:
- Se folosesc scripturile de benchmark din folderul benchmarks pentru a testa diferiți algoritmi.
- Pentru vizualizarea graficelor se rulează scripturile Python.
- Se folosește evaluate.ipynb pentru testări și vizualizări interactive.