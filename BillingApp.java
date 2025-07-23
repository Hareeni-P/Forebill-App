import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BillingApp {
    JFrame loginFrame, dashboardFrame;
    String validUsername = "admin", validPassword = "1234";
    Map<String, Double> originalPriceMap = new HashMap<>();
    ArrayList<Transaction> transactionList = new ArrayList<>();

    // Theme Colors
    Color primaryColor = Color.BLACK;         // Black background
    Color foregroundColor = Color.YELLOW;     // Gold-like foreground
    Color backgroundColor = Color.BLACK;      // Full black background

    public static void main(String[] args) {
        new BillingApp().showLogin();
    }

    void showLogin() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(new GridLayout(5, 1));
        loginFrame.getContentPane().setBackground(backgroundColor);

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        userField.setBackground(Color.WHITE);
        passField.setBackground(Color.WHITE);
        loginBtn.setBackground(primaryColor);
        loginBtn.setForeground(foregroundColor);

        loginFrame.add(new JLabel("Username:")).setForeground(foregroundColor);
        loginFrame.add(userField);
        loginFrame.add(new JLabel("Password:")).setForeground(foregroundColor);
        loginFrame.add(passField);
        loginFrame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (user.equals(validUsername) && pass.equals(validPassword)) {
                loginFrame.dispose();
                showDashboard();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid login");
            }
        });

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);    }

    void showDashboard() {
        dashboardFrame = new JFrame("Dashboard");
        dashboardFrame.setSize(400, 400);
        dashboardFrame.setLayout(new GridLayout(5, 1));
        dashboardFrame.getContentPane().setBackground(backgroundColor);

       JButton billingBtn = new JButton("🧾 Billing Section");
       JButton originalPriceBtn= new JButton("💰 Original Price Entry");
       JButton profitLossBtn = new JButton("📈 Profit & Loss");
       JButton salesDataBtn = new JButton("📊 Sales Data");
       JButton LogoutBtn = new JButton("🔒 Logout");

        JButton[] buttons = {billingBtn, originalPriceBtn, profitLossBtn, salesDataBtn,LogoutBtn};
        for (JButton btn : buttons) {
            btn.setBackground(primaryColor);
            btn.setForeground(foregroundColor);
            dashboardFrame.add(btn);
        }

         billingBtn.addActionListener(e -> {
            dashboardFrame.dispose();
            showBillingSection();
        });

         originalPriceBtn.addActionListener(e -> {
            dashboardFrame.dispose();
            showOriginalPriceEntry();
        });

         profitLossBtn.addActionListener(e -> {
            dashboardFrame.dispose();
            showProfitLossPage();
        });

        salesDataBtn.addActionListener(e -> {
            dashboardFrame.dispose();
            showSalesDataPage();
        });

         LogoutBtn.addActionListener(e -> {
            dashboardFrame.dispose();
            showLogin();
        });

        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setVisible(true);
    }

    void showBillingSection() {
        JFrame billFrame = new JFrame("Billing Section");
        billFrame.setSize(600, 400);
        billFrame.getContentPane().setBackground(backgroundColor);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBackground(backgroundColor);

        JTextField itemField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField qtyField = new JTextField();
        JButton addBtn = new JButton("Add");
        addBtn.setBackground(primaryColor);
        addBtn.setForeground(foregroundColor);

        JLabel itemLabel = new JLabel("Item Name:");
        itemLabel.setForeground(foregroundColor);
        JLabel priceLabel = new JLabel("Selling Price:");
        priceLabel.setForeground(foregroundColor);
        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setForeground(foregroundColor);

        panel.add(itemLabel); panel.add(itemField);
        panel.add(priceLabel); panel.add(priceField);
        panel.add(qtyLabel); panel.add(qtyField);
        panel.add(addBtn);

        DefaultTableModel billModel = new DefaultTableModel(
            new String[]{"Item", "Qty", "Price", "Total"}, 0);
        JTable billTable = new JTable(billModel);

        JButton backBtn = new JButton("\u2190 Back to Dashboard");
        backBtn.setBackground(primaryColor);
        backBtn.setForeground(foregroundColor);

        billFrame.setLayout(new BorderLayout());
        billFrame.add(panel, BorderLayout.NORTH);
        billFrame.add(new JScrollPane(billTable), BorderLayout.CENTER);
        billFrame.add(backBtn, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String item = itemField.getText();
            double sp = Double.parseDouble(priceField.getText());
            int qty = Integer.parseInt(qtyField.getText());
            double total = sp * qty;

            billModel.addRow(new Object[]{item, qty, sp, total});
            double op = originalPriceMap.getOrDefault(item, 0.0);
            transactionList.add(new Transaction(item, qty, sp, op));

            itemField.setText(""); priceField.setText(""); qtyField.setText("");
        });

        backBtn.addActionListener(e -> {
            billFrame.dispose();
            showDashboard();
        });

        billFrame.setVisible(true);
    }

    void showOriginalPriceEntry() {
        JFrame opFrame = new JFrame("Original Price Entry");
        opFrame.setSize(400, 200);
        opFrame.getContentPane().setBackground(backgroundColor);

        JTextField itemField = new JTextField();
        JTextField priceField = new JTextField();
        JButton saveBtn = new JButton("Save");
        JButton backBtn = new JButton("\u2190 Back to Dashboard");

        saveBtn.setBackground(primaryColor);
        saveBtn.setForeground(foregroundColor);
        backBtn.setBackground(primaryColor);
        backBtn.setForeground(foregroundColor);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBackground(backgroundColor);

        JLabel itemLabel = new JLabel("Item Name:");
        itemLabel.setForeground(foregroundColor);
        JLabel priceLabel = new JLabel("Original Price:");
        priceLabel.setForeground(foregroundColor);

        panel.add(itemLabel); panel.add(itemField);
        panel.add(priceLabel); panel.add(priceField);
        panel.add(saveBtn); panel.add(backBtn);

        saveBtn.addActionListener(e -> {
            String item = itemField.getText();
            double price = Double.parseDouble(priceField.getText());
            originalPriceMap.put(item, price);
        
            itemField.setText(""); priceField.setText("");
        });

        backBtn.addActionListener(e -> {
            opFrame.dispose();
            showDashboard();
        });

        opFrame.add(panel);
        opFrame.setVisible(true);
    }

    void showProfitLossPage() {
        JFrame plFrame = new JFrame("Profit & Loss");
        plFrame.setSize(600, 300);
        plFrame.getContentPane().setBackground(backgroundColor);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Item", "Qty", "SP", "OP", "Profit/Loss"}, 0);
        JTable table = new JTable(model);

        for (Transaction t : transactionList) {
            model.addRow(new Object[]{t.item, t.qty, t.sp, t.op, t.result});
        }

        JButton backBtn = new JButton("\u2190 Back to Dashboard");
        backBtn.setBackground(primaryColor);
        backBtn.setForeground(foregroundColor);

        plFrame.add(new JScrollPane(table), BorderLayout.CENTER);
        plFrame.add(backBtn, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            plFrame.dispose();
            showDashboard();
        });

        plFrame.setVisible(true);
    }

    void showSalesDataPage() {
        JFrame salesFrame = new JFrame("Sales Data");
        salesFrame.setSize(700, 300);
        salesFrame.getContentPane().setBackground(backgroundColor);

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Item", "Qty", "SP", "OP", "Profit/Loss"}, 0);
        JTable table = new JTable(model);

        for (Transaction t : transactionList) {
            model.addRow(new Object[]{t.item, t.qty, t.sp, t.op, t.result});
        }

        JButton backBtn = new JButton("\u2190 Back to Dashboard");
        backBtn.setBackground(primaryColor);
        backBtn.setForeground(foregroundColor);

        salesFrame.add(new JScrollPane(table), BorderLayout.CENTER);
        salesFrame.add(backBtn, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            salesFrame.dispose();
            showDashboard();
        });

        salesFrame.setVisible(true);
    }
}

class Transaction {
    String item;
    int qty;
    double sp, op;
    String result;

    Transaction(String item, int qty, double sp, double op) {
        this.item = item;
        this.qty = qty;
        this.sp = sp;
        this.op = op;
        double pl = (sp - op) * qty;
        this.result = pl >= 0 ? "Profit: " + pl : "Loss: " + (-pl);
    }
}
