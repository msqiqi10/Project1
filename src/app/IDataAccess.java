package app;
public interface IDataAccess {
    public static final int CONNECTION_OPEN_OK = 1;
    public static final int CONNECTION_OPEN_FAILED = 2;

    public static final int PRODUCT_SAVE_OK = 101;
    public static final int PRODUCT_SAVE_FAILED = 102;
    public static final int PRODUCT_SAVE_DUPLICATE = 103;

    public static final int PRODUCT_LOAD_OK = 201;
    public static final int PRODUCT_LOAD_FAILED = 202;
    public static final int PRODUCT_LOAD_ID_NOT_FOUND = 203;

    public static final int CUSTOMER_LOAD_OK = 301;
    public static final int CUSTOMER_LOAD_FAILED = 302;
    public static final int CUSTOMER_LOAD_ID_NOT_FOUND = 303;

    public boolean connect(String path);
    public boolean disconnect();
    public int getErrorCode();
    public String getErrorMessage();

    public ProductModel loadProduct(int id);
    public boolean saveProduct(ProductModel product);

    public CustomerModel loadCustomer(int mProductID);
    public boolean saveCustomer(CustomerModel customer);

    public PurchaseModel loadPurchase(int mPurchaseID);
    public boolean savePurchase(PurchaseModel purchase);
}
