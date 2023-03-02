#
#  JVM Performance Benchmarks
#
#  Copyright (C) 2019 - 2022 Ionut Balosin
#  Website: www.ionutbalosin.com
#  Twitter: @ionutbalosin
#
#  Co-author: Florin Blanaru
#  Twitter: @gigiblender
#
#  This program is free software: you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#
#  You should have received a copy of the GNU General Public License
#  along with this program.  If not, see <http://www.gnu.org/licenses/>.
#

source("./ggplot2/split-utils.r")

# retrieve command line arguments in a very specific order
args <- commandArgs(TRUE)
jmh_output_folder <- args[1]
openjdk_hotspot_vm_identifier <- args[2]
graalvm_ce_identifier <- args[3]
graalvm_ee_identifier <- args[4]

# Split the benchmark result files based on the column name and values criteria differentiator

# Split FactorialBenchmark
benchmark_file <- "FactorialBenchmark.csv"
param_name <- "Param..n"
param_values <- list("1000")
output_file <- "FactorialBenchmark_param_n_1000.csv"
processJmhCsvResults(jmh_output_folder, openjdk_hotspot_vm_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ce_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ee_identifier, benchmark_file, param_name, param_values, output_file)

benchmark_file <- "FactorialBenchmark.csv"
param_name <- "Param..n"
param_values <- list("5000")
output_file <- "FactorialBenchmark_param_n_5000.csv"
processJmhCsvResults(jmh_output_folder, openjdk_hotspot_vm_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ce_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ee_identifier, benchmark_file, param_name, param_values, output_file)

# Split FibonacciBenchmark
benchmark_file <- "FibonacciBenchmark.csv"
param_name <- "Param..n"
param_values <- list("500")
output_file <- "FibonacciBenchmark_param_n_500.csv"
processJmhCsvResults(jmh_output_folder, openjdk_hotspot_vm_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ce_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ee_identifier, benchmark_file, param_name, param_values, output_file)

benchmark_file <- "FibonacciBenchmark.csv"
param_name <- "Param..n"
param_values <- list("3000")
output_file <- "FibonacciBenchmark_param_n_3000.csv"
processJmhCsvResults(jmh_output_folder, openjdk_hotspot_vm_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ce_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ee_identifier, benchmark_file, param_name, param_values, output_file)

# Split NpeControlFlowBenchmark
benchmark_file <- "NpeControlFlowBenchmark.csv"
param_name <- "Param..nullThreshold"
param_values <- list("0")
output_file <- "NpeControlFlowBenchmark_param_nullThreshold_0.csv"
processJmhCsvResults(jmh_output_folder, openjdk_hotspot_vm_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ce_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ee_identifier, benchmark_file, param_name, param_values, output_file)

benchmark_file <- "NpeControlFlowBenchmark.csv"
param_name <- "Param..nullThreshold"
param_values <- list("16")
output_file <- "NpeControlFlowBenchmark_param_nullThreshold_16.csv"
processJmhCsvResults(jmh_output_folder, openjdk_hotspot_vm_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ce_identifier, benchmark_file, param_name, param_values, output_file)
processJmhCsvResults(jmh_output_folder, graalvm_ee_identifier, benchmark_file, param_name, param_values, output_file)