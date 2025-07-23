import sys
def predict(op, qty):
    profit = op * qty * 0.3  # Dummy model logic
    print(profit)

if __name__ == "__main__":
    op = float(sys.argv[1])
    qty = int(sys.argv[2])
    predict(op, qty)
