package app;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDataAdapter implements IDataAccess {
    Connection conn = null;
    int errorCode = 0;

    public boolean connect(String path) {
        try {
            // db parameters
            String url = "jdbc:sqlite:" + path;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            errorCode = CONNECTION_OPEN_FAILED;
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        return true;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        switch (errorCode) {
            case CONNECTION_OPEN_FAILED: return "Connection is not opened!";
            case PRODUCT_LOAD_FAILED: return "Cannot load the product!";
        };
        return "OK";
    }

    public ProductModel loadProduct(int productID) {
        try {
            ProductModel product = new ProductModel();

            String sql = "SELECT mProductId, mName, mPrice, mQuantity, mVendor FROM Products WHERE mProductId = " + productID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            product.mProductID = rs.getInt("mProductId");
            product.mName = rs.getString("mName");
            product.mPrice = rs.getDouble("mPrice");
            product.mQuantity = rs.getDouble("mQuantity");
            product.mVendor = rs.getString("mVendor");
            return product;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCode = PRODUCT_LOAD_FAILED;
            return null;
        }
    }

    public CustomerModel loadCustomer(int id) {
        try {
            CustomerModel c = new CustomerModel();

            String sql = "SELECT * FROM Customers WHERE mCustomerID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            c.mCustomerID = rs.getInt("mCustomerID");
            c.mName = rs.getString("mName");
            c.mPhone = rs.getString("mPhone");
            c.mAddress = rs.getString("mAddress");
            return c;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCode = CUSTOMER_LOAD_FAILED;
            return null;
        }
    }

    public PurchaseModel loadPurchase(int id) {
        try {
            PurchaseModel p = new PurchaseModel();

            String sql = "SELECT * FROM Purchases WHERE mPurchaseID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            p.mCustomerID = rs.getInt("mCustomerID");
            p.mPurchaseID = rs.getInt("mPurchaseID");
            p.mProductID = rs.getInt("mProductID");
            p.mDate = rs.getString("mDate");
            p.mQuantity = rs.getInt("mQuantity");
            p.mPrice = rs.getDouble("mPrice");
            p.mCost = rs.getDouble("mCost");
            p.mTax = rs.getDouble("mTax");
            p.mTotalCost = rs.getDouble("mTotalCost");
            return p;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorCode = CUSTOMER_LOAD_FAILED;
            return null;
        }
    }

    public boolean saveProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "  " + product.mProductID + ", \"" + product.mName + "\", \"" + product.mPrice + "\", \"" + product.mQuantity + "\", \"" + product.mVendor +"\")";
            int t = stmt.executeUpdate(sql);
            if(t == 1){ return true;}
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean saveCustomer(CustomerModel customer) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Customers (mCustomerID, mName, mPhone, mAddress) VALUES (" + customer.mCustomerID + ", \"" + customer.mName + "\", \"" + customer.mPhone + "\", \"" + customer.mAddress +"\")";
            int t = stmt.executeUpdate(sql);
            if(t == 1){ return true;}
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean savePurchase(PurchaseModel purchase){
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Purchases (mPurchaseID, mCustomerID, mProductID, mPrice, mQuantity, mTax, mTotalCost, mCost, mDate) " +
                    "VALUES (" + purchase.mPurchaseID + ", \"" + purchase.mCustomerID + "\", \"" + purchase.mProductID + "\"," +
                    " \"" + purchase.mPrice + "\", \"" + purchase.mQuantity + "\", \"" + purchase.mTax+ "\", \"" + purchase.mTotalCost+ "\", \""
                    + purchase.mCost + "\", \"" + purchase.mDate +"\")";
            int t = stmt.executeUpdate(sql);
            if(t == 1){ return true;}
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
