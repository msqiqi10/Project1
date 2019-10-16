package app;
import javax.swing.*;

public class StoreManager {
    public static String DEFAULT_DB_FILE = "data.db";
    IDataAccess mAdapter;
    MainUI mMainUI;

    static StoreManager instance = null;

    public static StoreManager getInstance() {
        if (instance == null) {
            instance = new StoreManager();
            instance.setup("SQLite", true);
        }
        return instance;
    }

    public IDataAccess getDataAccess() {
        return mAdapter;
    }

    private void setup(String dbms, boolean cache) {
        if (dbms.equals("SQLite"))
            mAdapter = new SQLiteDataAdapter();
//        if (dbms.equals("Oracle"))
//            adapter = new OracleDataAdapter();

//        if (cache)
//            mAdapter = new CachedDataAdapter(mAdapter);

        String dbFile = DEFAULT_DB_FILE;
        if (dbFile.length() == 0) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Select the DB file!!!");
            int r = fc.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION)
                dbFile = fc.getSelectedFile().getAbsolutePath();
        }
        if (mAdapter.connect(dbFile))
            System.out.println("Connection to SQLite has been established.");
        else
            System.out.println(mAdapter.getErrorMessage());

        mMainUI = new MainUI();
    }

    public void run() {
        mMainUI.view.setVisible(true);
    }

}
