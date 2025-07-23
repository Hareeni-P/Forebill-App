import sys
import pandas as pd
from fpdf import FPDF

# Get sales type from command-line (week / month / year)
data_type = sys.argv[1]  # example: 'week'
file_map = {
    "week": "week_data.csv",
    "month": "month_data.csv",
    "year": "year_data.csv"
}

# Read CSV file
csv_file = file_map[data_type]
df = pd.read_csv(csv_file)

# Create PDF
pdf = FPDF()
pdf.add_page()
pdf.set_font("Arial", size=12)
pdf.cell(200, 10, txt=f"{data_type.capitalize()} Sales Report", ln=True, align='C')

for index, row in df.iterrows():
    row_text = ', '.join([str(item) for item in row])
    pdf.cell(200, 10, txt=row_text, ln=True)

# Save PDF
pdf.output(f"{data_type}_sales_report.pdf")
print(f"{data_type}_sales_report.pdf created successfully")
