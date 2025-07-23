import pandas as pd
from sklearn.linear_model import LinearRegression
import joblib

# 🔹 CSV file read pannuthu
data = pd.read_csv("sales_data.csv", header=None)
data.columns = ["Product", "Quantity", "Price"]

# 🔹 Features (X) & Target (y)
X = data[["Quantity"]]   # Quantity → input
y = data["Price"]        # Price → output

# 🔹 Linear Regression model train pannuthu
model = LinearRegression()
model.fit(X, y)

# 🔹 Trained model ah save pannuthu
joblib.dump(model, "model.pkl")

print("✅ Model training complete! Saved as model.pkl")
