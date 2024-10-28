## Usage Instructions
To use this tool, follow these steps:

1. **Clone the Repository** (if you haven't already):
   ```sh
   git clone https://github.com/Hishamk2/tech-debt-satd.git
   ```
   
2. **Navigate to the Project Directory:**
   ```sh
   cd Bug-prediction
   ```

3. **Run the Complexity Analyzer Tool:**
   
   Execute the following command from within the `Bug-prediction` directory to run the tool:
   ```sh
   java -Xmx10240m -jar target/complexityAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar -projectsInfo ../settings-selected-project.txt {source_methods_dir} -resultDir ../results/ -filterOutTestMethods true
   ```
   Replace `{source_methods_dir}` with the directory that contains the source code methods for analysis.

### Command Parameters
- **`-projectsInfo`**: Path to the project settings file (`../settings-selected-project.txt`). This file contains the list of projects to be analyzed. Righto now, there are and should be 49 projects
- **`{source_methods_dir}`**: Directory containing source method files to analyze. Replace with your specific directory path. (Not incuded in this repo as the folder is really large)
- **`-resultDir`**: Directory to store analysis results. In this case, results will be stored in `../results/`.
- **`-filterOutTestMethods true`**: Boolean flag to exclude test methods from the analysis.
