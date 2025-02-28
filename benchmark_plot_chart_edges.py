import pandas as pd
import matplotlib.pyplot as plt

benchmark_results_path = './Benchmark_Results_VertexCoverBrute.csv'
graph_test_data_path = './Graph_Test_Data.csv'

benchmark_results_df = pd.read_csv(benchmark_results_path)
graph_test_data_df = pd.read_csv(graph_test_data_path)

# Uniformizarea coloanei TestNumber pentru a include prefixul 'Test '
benchmark_results_df['TestNumber'] = benchmark_results_df['TestNumber'].apply(lambda x: f'Test {x}')
graph_test_data_df['TestNumber'] = graph_test_data_df['TestNumber'].apply(lambda x: str(x))

benchmark_results_df['Error'] = benchmark_results_df['Error'].str.replace('±', '').str.strip().astype(float)

merged_df = pd.merge(graph_test_data_df, benchmark_results_df, on='TestNumber')

merged_df = merged_df.sort_values(by='Edges', ascending=True)

merged_df['Score'] = pd.to_numeric(merged_df['Score'], errors='coerce')

plt.figure(figsize=(10, 6))
plt.errorbar(merged_df['Edges'], merged_df['Score'], yerr=merged_df['Error'], fmt='o-', ecolor='red', capsize=5, linestyle='-', color='blue', label='Score with Confidence Interval')
plt.title('Score vs. Number of Edges with Confidence Interval')
plt.xlabel('Number of Edges')
plt.ylabel('Score (ms/op)')
plt.grid(True)
plt.legend()
plt.show()