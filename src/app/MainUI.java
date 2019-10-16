package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    public JFrame view;
    public JButton btnAddProduct = new JButton("Add Product");
    public JButton btnAddCustomer = new JButton("Add Customer");
    public JButton btnAddPurchase = new JButton("Add Purchase");

    public MainUI() {
        view = new JFrame();
        view.setTitle("Store Management System");
        view.setSize(1000, 600);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = view.getContentPane();
        pane.setLayout(new BorderLayout());

        JLabel title = new JLabel("Store Management System");
        title.setFont(new Font("Serif", Font.BOLD, 32));

        JPanel titlePane = new JPanel(new FlowLayout());

        titlePane.add(title);
        pane.add(titlePane, BorderLayout.PAGE_START);

        JPanel buttonPane = new JPanel(new FlowLayout());
        buttonPane.add(btnAddProduct);
        buttonPane.add(btnAddCustomer);
        buttonPane.add(btnAddPurchase);
        pane.add(buttonPane);

        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddProductUI ui = new AddProductUI();
                ui.run();
            }
        });

        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddCustomerUI ui = new AddCustomerUI();
                ui.run();
            }
        });

        btnAddPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ui = new AddPurchaseUI();
                ui.run();
            }
        });
    }
}