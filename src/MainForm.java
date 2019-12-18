// Package Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// MainForm Class
public class MainForm extends javax.swing.JFrame {
    // url for image on input screen
    private final String InputImageUrl = "inputimage.jpg";
    
    // this detects if a key is pressed
    public KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
        public boolean dispatchKeyEvent(final KeyEvent e) {
            // if that key is enter
            if (e.getKeyCode()== KeyEvent.VK_ENTER) {
                try{
                    // changes to the output page when enter is pressed only if we are on the input page
                    if(tabMain.getSelectedIndex() == 0){
                        tabMain.setSelectedIndex(1);
                    }
                }catch(Exception ex){
                    log(ex.getMessage());
                }
          }
          return false;
        }
      };
    // formatting and general constants
    public int posX = 0;
    public int posY = 0;
    private int OutputLabelWidth = 275;
    private String OutputLabelFontName = "Verdana"; //"Segoe UI";
    private int OutputLabelFontSize = 20;
    private int OutputValueLabelHeight = 25;
    private int CustomBarWidth = 400;
    private int CustomBarHeight = 45;
    private int CustomBarPaddingTop = 15;
    private int OutputLabelPaddingLeft = 5;
    private int OutputMetricLabelPaddingLeft = 5;
    private int CustomBarPaddingLeft = 630;
    private int OutputValueLabelPaddingLeft;
    private int FormWidth = 1500;
    private int FormHeight = 1000;
    
    // initialisation of the calculator object
    public Calculator calculator = new Calculator();
    // Tooltips array
    private ArrayList<String> Tooltips = new ArrayList<String>(
            Arrays.asList(
                "Debt-Service Coverage Ratio (DSCR) is a measure of the cash flow available to pay current debt obligations.", 
                "Loan to Value Ratio (LTV) describes the size of a loan compared to the value of the property securing the loan.", 
                "Gross Rental Yield (GRY) is the rental income as a percentage of the property's value.", 
                "Price to income Ratio (P/E) is the measure of the property price relative to the annual net income earned by property.", 
                "Operating Expense Ratio (OER) is a measure of what it costs to operate a piece of property compared to the income that the property brings in.", 
                "Capitalization Rate (cap rate) is the ratio of Net Operating Income (NOI) to property asset value.", 
                "Internal rate of return (IRR) is the interest rate at which the net present value of all the cash flows (both positive and negative) from a project or investment equal zero", 
                "Gross Rent Multiplier (GRM) is the ratio of the sale or purchase price of a real estate deal to its annual rental income before deducting operating costs", 
                "Return On Investment (ROI) is a measure which is used to evaluate the efficiency, or profitability, of an investment. "
                
            ));
    // Log function for ease of debuging
    public void log(Object s){
        System.out.println("REALSESTATE: " + String.valueOf(s));
    }
    // panel to make the output section relitivly centered on startup
    private JPanel pnlOutputCenter = new JPanel(new BorderLayout());
    // array to determine what the spread of the normalised distribution of the output custom bar values from calculator
    private ArrayList<Float> OutputMetricSpreads = new ArrayList<Float>(
            Arrays.asList(
                    1.0f, 0.7f, 0.65f, 0.3f, 0.4f, 0.85f, 0.6f, 0.6f, 0.15f, 0.65f , 0.4f
            ));
    // the lower section labels
    private ArrayList<String> OutputValueMetricText = new ArrayList<String>(
            Arrays.asList(
                    "Cash On Cash Return", "Cash Flow", "Mortgage Repayments", "Net Operating Income", "Gross Scheduled Income", "Gross Operating Income", "Operating Expenses", "Property Taxes", "Property Insurance", "Property Management", "Maintenance and Repairs", "Landscaping", "Legal", "Accounting", "Telephone", "Office Supplies", "Cleaning Service", "Pest Control", "Advertising", ""
            ));
    // the upper section custom bar labels
    private ArrayList<String> OutputCustomBarMetricText = new ArrayList<String>(
            Arrays.asList(
                    "Debt Coverage Ratio", "Loan To Value Ratio", "Gross Rental Yield", "Cash Break Even Ratio", "Price To Income Ratio", "Operating Expense Ratio", "Capitalization Rate", "Internal Rate of Return", "Gross Rental Multiplier", "Return on Investment"
            ));
    // the values for the custombars (percentages and ratios)
    private ArrayList<JLabel> CustomBarLabels = new ArrayList<JLabel>();
    // the panels that contain the custombar to provent size changing with position
    private ArrayList<JPanel> CustomBarContainers = new ArrayList<JPanel>();
    // the values for the lower output section
    private ArrayList<JLabel> OutputValueMetricLabels = new ArrayList<JLabel>();
    // the labels for the output section
    private ArrayList<JLabel> OutputMetrics = new ArrayList<JLabel>();
    // the array that holds the instances of the custombars
    private ArrayList<CustomBar> CustomBars = new ArrayList<CustomBar>();
    
    public MainForm() {
        // Initilise the UI
        initComponents();
        // init the keyboard manager to detect enter key (see above)
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
        
        // try-catch incase the image isnt preasent
        try {
            // set icon the the file inputimageurl - scaled
            lblInputImage.setIcon(new ImageIcon(ImageIO.read(new File(InputImageUrl)).getScaledInstance(lblInputImage.getWidth(), lblInputImage.getHeight(), Image.SCALE_SMOOTH)));
            // set the size based on its container
            lblInputImage.setSize(pnlInputRight.getWidth(), pnlInputRight.getHeight());
        } catch (IOException ex) {
            // print error - dont crash
            ex.printStackTrace();
        }
        // init handles pretty much all of the output page stuff
        init();
        
        // makes the form the size we want
        this.setSize(FormWidth, FormHeight);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeading = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlRoot = new javax.swing.JPanel();
        tabMain = new javax.swing.JTabbedPane();
        pnlInputTab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlInput = new javax.swing.JPanel();
        pnlInputLeft = new javax.swing.JPanel();
        lblMarketValue = new javax.swing.JLabel();
        lblPropertyIncome = new javax.swing.JLabel();
        txtMarketValue = new javax.swing.JTextField();
        txtPropertyIncome = new javax.swing.JTextField();
        lblPropertyIncomePer = new javax.swing.JLabel();
        cmbPropertyIncomePeriod = new javax.swing.JComboBox<>();
        lblDeposit = new javax.swing.JLabel();
        txtDeposit = new javax.swing.JTextField();
        cmbDepositUnit = new javax.swing.JComboBox<>();
        lblPropertyManagement = new javax.swing.JLabel();
        btnPropertyManagement = new javax.swing.JToggleButton();
        lblYearBuilt = new javax.swing.JLabel();
        txtYearBuilt = new javax.swing.JTextField();
        pnlInputRight = new javax.swing.JPanel();
        lblInputImage = new javax.swing.JLabel();
        pnlOutputTab = new javax.swing.JPanel();
        scrOutput = new javax.swing.JScrollPane();
        pnlOutput = new javax.swing.JPanel();
        pnlSettingsTab = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblCostsAccounting = new javax.swing.JLabel();
        txtCostsAccounting = new javax.swing.JTextField();
        txtCostsOffice = new javax.swing.JTextField();
        lblCostsOffice = new javax.swing.JLabel();
        txtCostsPhone = new javax.swing.JTextField();
        lblCostsPhone = new javax.swing.JLabel();
        lblInterestRate = new javax.swing.JLabel();
        txtCostsRepair = new javax.swing.JTextField();
        lblCPI = new javax.swing.JLabel();
        txtCPI = new javax.swing.JTextField();
        lblCostsRepair = new javax.swing.JLabel();
        lblCostsJanitorial = new javax.swing.JLabel();
        lblCostsLegal = new javax.swing.JLabel();
        txtCostsJanitorial = new javax.swing.JTextField();
        txtCostsLegal = new javax.swing.JTextField();
        txtInterestRate = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        txtVacency = new javax.swing.JTextField();
        lblPropertyInsuranceEstimate = new javax.swing.JLabel();
        lblPersonalIncome = new javax.swing.JLabel();
        lblPropertyManagementEstimate = new javax.swing.JLabel();
        lblPersonalExpenses = new javax.swing.JLabel();
        txtPropertyManagementEstimate = new javax.swing.JTextField();
        txtPersonalIncome = new javax.swing.JTextField();
        txtPersonalExpenses = new javax.swing.JTextField();
        txtInternalRateOfReturnIterations = new javax.swing.JTextField();
        lblPersonalIncomePer = new javax.swing.JLabel();
        lblPersonalExpensesPer = new javax.swing.JLabel();
        lblInternalRateOfReturnIterations = new javax.swing.JLabel();
        cmbPersonalIncomePeriod = new javax.swing.JComboBox<>();
        txtLoanPeriod = new javax.swing.JTextField();
        txtTimesInterestCalculatedPerYear = new javax.swing.JTextField();
        cmbPersonalExpensesPeriod = new javax.swing.JComboBox<>();
        lblTimesInterestCalculatedPerYear = new javax.swing.JLabel();
        lblLoanPeriod = new javax.swing.JLabel();
        lblVacency = new javax.swing.JLabel();
        txtPropertyInsuranceEstimate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnFontTabs = new javax.swing.JButton();
        lblFontTabs = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblFontButtons = new javax.swing.JLabel();
        btnFontButtons = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        lblFontInputsAndOutputs = new javax.swing.JLabel();
        btnFontInputsAndOutputs = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        btnAdvanced = new javax.swing.JToggleButton();
        jSeparator6 = new javax.swing.JSeparator();
        pnlStatusBar = new javax.swing.JPanel();
        btnApplySettings = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realestate Calculator");
        setBackground(new java.awt.Color(250, 250, 250));
        setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        setLocation(new java.awt.Point(150, 25));
        setName("MainForm"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlHeading.setBackground(new java.awt.Color(44, 62, 80));

        lblTitle.setBackground(new java.awt.Color(44, 62, 80));
        lblTitle.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 72)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Realestate  Calculator");
        lblTitle.setDoubleBuffered(true);

        javax.swing.GroupLayout pnlHeadingLayout = new javax.swing.GroupLayout(pnlHeading);
        pnlHeading.setLayout(pnlHeadingLayout);
        pnlHeadingLayout.setHorizontalGroup(
            pnlHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHeadingLayout.setVerticalGroup(
            pnlHeadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRoot.setBackground(new java.awt.Color(250, 250, 250));

        tabMain.setBackground(new java.awt.Color(250, 250, 250));
        tabMain.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabMain.setAutoscrolls(true);
        tabMain.setDoubleBuffered(true);
        tabMain.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 24)); // NOI18N
        tabMain.setInheritsPopupMenu(true);
        tabMain.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabMainStateChanged(evt);
            }
        });

        pnlInputTab.setBackground(new java.awt.Color(250, 250, 250));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setDoubleBuffered(true);

        pnlInput.setBackground(new java.awt.Color(250, 250, 250));
        pnlInput.setPreferredSize(new java.awt.Dimension(936, 6008));
        pnlInput.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlInputComponentResized(evt);
            }
        });
        pnlInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnlInputKeyPressed(evt);
            }
        });

        pnlInputLeft.setBackground(new java.awt.Color(44, 62, 80));
        pnlInputLeft.setPreferredSize(new java.awt.Dimension(468, 468));

        lblMarketValue.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMarketValue.setForeground(new java.awt.Color(255, 255, 255));
        lblMarketValue.setText("Market Value: ($)");
        lblMarketValue.setAlignmentX(0.5F);
        lblMarketValue.setAutoscrolls(true);

        lblPropertyIncome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPropertyIncome.setForeground(new java.awt.Color(255, 255, 255));
        lblPropertyIncome.setText("Property Income: ($)");
        lblPropertyIncome.setAlignmentX(0.5F);
        lblPropertyIncome.setAutoscrolls(true);

        txtMarketValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMarketValue.setToolTipText("");
        txtMarketValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarketValueActionPerformed(evt);
            }
        });

        txtPropertyIncome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblPropertyIncomePer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPropertyIncomePer.setForeground(new java.awt.Color(255, 255, 255));
        lblPropertyIncomePer.setText("Per");
        lblPropertyIncomePer.setAlignmentX(0.5F);
        lblPropertyIncomePer.setAutoscrolls(true);

        cmbPropertyIncomePeriod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbPropertyIncomePeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Week", "Fortnight", "Month", "Quarter", "Year" }));
        cmbPropertyIncomePeriod.setAutoscrolls(true);

        lblDeposit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDeposit.setForeground(new java.awt.Color(255, 255, 255));
        lblDeposit.setText("Deposit: ");
        lblDeposit.setAlignmentX(0.5F);
        lblDeposit.setAutoscrolls(true);

        txtDeposit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepositActionPerformed(evt);
            }
        });

        cmbDepositUnit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbDepositUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "%", "$" }));
        cmbDepositUnit.setAutoscrolls(true);

        lblPropertyManagement.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPropertyManagement.setForeground(new java.awt.Color(255, 255, 255));
        lblPropertyManagement.setText("Property Management:");
        lblPropertyManagement.setAlignmentX(0.5F);
        lblPropertyManagement.setAutoscrolls(true);

        btnPropertyManagement.setBackground(new java.awt.Color(0, 255, 0));
        btnPropertyManagement.setText("NO");
        btnPropertyManagement.setAlignmentX(0.5F);
        btnPropertyManagement.setAutoscrolls(true);
        btnPropertyManagement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnPropertyManagement.setFocusPainted(false);
        btnPropertyManagement.setFocusable(false);
        btnPropertyManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPropertyManagementActionPerformed(evt);
            }
        });

        lblYearBuilt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblYearBuilt.setForeground(new java.awt.Color(255, 255, 255));
        lblYearBuilt.setText("Year House Was Built:");
        lblYearBuilt.setAlignmentX(0.5F);
        lblYearBuilt.setAutoscrolls(true);

        txtYearBuilt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtYearBuilt.setToolTipText("");
        txtYearBuilt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearBuiltActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInputLeftLayout = new javax.swing.GroupLayout(pnlInputLeft);
        pnlInputLeft.setLayout(pnlInputLeftLayout);
        pnlInputLeftLayout.setHorizontalGroup(
            pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPropertyIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPropertyManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarketValue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYearBuilt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtYearBuilt)
                    .addGroup(pnlInputLeftLayout.createSequentialGroup()
                        .addComponent(txtPropertyIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPropertyIncomePer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbPropertyIncomePeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPropertyManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMarketValue)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputLeftLayout.createSequentialGroup()
                        .addComponent(cmbDepositUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlInputLeftLayout.setVerticalGroup(
            pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputLeftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarketValue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarketValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPropertyIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPropertyIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPropertyIncomePer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPropertyIncomePeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDepositUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPropertyManagement, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnPropertyManagement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlInputLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYearBuilt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearBuilt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        pnlInputRight.setOpaque(false);
        pnlInputRight.setPreferredSize(new java.awt.Dimension(468, 468));

        javax.swing.GroupLayout pnlInputRightLayout = new javax.swing.GroupLayout(pnlInputRight);
        pnlInputRight.setLayout(pnlInputRightLayout);
        pnlInputRightLayout.setHorizontalGroup(
            pnlInputRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputRightLayout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(lblInputImage, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        pnlInputRightLayout.setVerticalGroup(
            pnlInputRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInputImage, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlInputLayout = new javax.swing.GroupLayout(pnlInput);
        pnlInput.setLayout(pnlInputLayout);
        pnlInputLayout.setHorizontalGroup(
            pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInputRight, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlInputLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInputLayout.setVerticalGroup(
            pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(pnlInputLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 45, Short.MAX_VALUE)
                .addComponent(pnlInputRight, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5086, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(pnlInput);

        javax.swing.GroupLayout pnlInputTabLayout = new javax.swing.GroupLayout(pnlInputTab);
        pnlInputTab.setLayout(pnlInputTabLayout);
        pnlInputTabLayout.setHorizontalGroup(
            pnlInputTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        pnlInputTabLayout.setVerticalGroup(
            pnlInputTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputTabLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabMain.addTab("        Input        ", pnlInputTab);

        pnlOutputTab.setBackground(new java.awt.Color(250, 250, 250));

        scrOutput.setBorder(null);
        scrOutput.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrOutput.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrOutput.setDoubleBuffered(true);

        pnlOutput.setBackground(new java.awt.Color(250, 250, 250));
        pnlOutput.setOpaque(false);

        javax.swing.GroupLayout pnlOutputLayout = new javax.swing.GroupLayout(pnlOutput);
        pnlOutput.setLayout(pnlOutputLayout);
        pnlOutputLayout.setHorizontalGroup(
            pnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1552, Short.MAX_VALUE)
        );
        pnlOutputLayout.setVerticalGroup(
            pnlOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1600, Short.MAX_VALUE)
        );

        scrOutput.setViewportView(pnlOutput);

        javax.swing.GroupLayout pnlOutputTabLayout = new javax.swing.GroupLayout(pnlOutputTab);
        pnlOutputTab.setLayout(pnlOutputTabLayout);
        pnlOutputTabLayout.setHorizontalGroup(
            pnlOutputTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrOutput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        pnlOutputTabLayout.setVerticalGroup(
            pnlOutputTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        tabMain.addTab("Output", pnlOutputTab);

        jPanel13.setBackground(new java.awt.Color(44, 62, 80));

        jPanel15.setPreferredSize(new java.awt.Dimension(400, 400));

        lblCostsAccounting.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCostsAccounting.setText("Accounting Costs Estimate:");

        txtCostsAccounting.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCostsAccounting.setAutoscrolls(false);

        txtCostsOffice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCostsOffice.setAutoscrolls(false);

        lblCostsOffice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCostsOffice.setText("Office Supplies Estimate:");

        txtCostsPhone.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCostsPhone.setAutoscrolls(false);

        lblCostsPhone.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCostsPhone.setText("Telephone Costs Estimate: ");

        lblInterestRate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInterestRate.setText("Interest Rate % Per annum: ");

        txtCostsRepair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCostsRepair.setAutoscrolls(false);

        lblCPI.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCPI.setText("CPI Index Change (%): ");

        txtCPI.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCPI.setAutoscrolls(false);

        lblCostsRepair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCostsRepair.setText("Repair Costs Estimate (%): ");

        lblCostsJanitorial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCostsJanitorial.setText("Cleaning Costs Estimate");

        lblCostsLegal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCostsLegal.setText("Legal Costs Estimate: ");

        txtCostsJanitorial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCostsJanitorial.setAutoscrolls(false);

        txtCostsLegal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCostsLegal.setAutoscrolls(false);

        txtInterestRate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtInterestRate.setAutoscrolls(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCostsPhone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCostsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCostsJanitorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCostsJanitorial, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblInterestRate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCostsAccounting)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCostsAccounting, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCostsOffice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCostsOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCostsRepair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCostsRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCPI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCPI, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblCostsLegal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCostsLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostsLegal)
                    .addComponent(txtCostsLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCostsAccounting)
                    .addComponent(txtCostsAccounting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostsPhone)
                    .addComponent(txtCostsPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostsOffice)
                    .addComponent(txtCostsOffice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostsJanitorial)
                    .addComponent(txtCostsJanitorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostsRepair)
                    .addComponent(txtCostsRepair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInterestRate)
                    .addComponent(txtInterestRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPI)
                    .addComponent(txtCPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setPreferredSize(new java.awt.Dimension(400, 400));

        txtVacency.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblPropertyInsuranceEstimate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPropertyInsuranceEstimate.setText("Property Insurance Estimate: ");

        lblPersonalIncome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPersonalIncome.setText("Personal Income:");
        lblPersonalIncome.setAlignmentX(0.5F);
        lblPersonalIncome.setAutoscrolls(true);

        lblPropertyManagementEstimate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPropertyManagementEstimate.setText("Property Management Estimate:");

        lblPersonalExpenses.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPersonalExpenses.setText("Personal Expenses:");
        lblPersonalExpenses.setAlignmentX(0.5F);
        lblPersonalExpenses.setAutoscrolls(true);

        txtPropertyManagementEstimate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPropertyManagementEstimate.setAutoscrolls(false);

        txtPersonalIncome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPersonalIncome.setAutoscrolls(false);

        txtPersonalExpenses.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPersonalExpenses.setAutoscrolls(false);

        txtInternalRateOfReturnIterations.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtInternalRateOfReturnIterations.setAutoscrolls(false);

        lblPersonalIncomePer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPersonalIncomePer.setText("Per");
        lblPersonalIncomePer.setAlignmentX(0.5F);
        lblPersonalIncomePer.setAutoscrolls(true);

        lblPersonalExpensesPer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPersonalExpensesPer.setText("Per");
        lblPersonalExpensesPer.setAlignmentX(0.5F);
        lblPersonalExpensesPer.setAutoscrolls(true);

        lblInternalRateOfReturnIterations.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInternalRateOfReturnIterations.setText("Internal Rate of Return Iterations: ");

        cmbPersonalIncomePeriod.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbPersonalIncomePeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Week", "Fortnight", "Month", "Quarter", "Year" }));
        cmbPersonalIncomePeriod.setAutoscrolls(true);

        txtLoanPeriod.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtLoanPeriod.setAutoscrolls(false);

        txtTimesInterestCalculatedPerYear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTimesInterestCalculatedPerYear.setAutoscrolls(false);

        cmbPersonalExpensesPeriod.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbPersonalExpensesPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Week", "Fortnight", "Month", "Quarter", "Year" }));
        cmbPersonalExpensesPeriod.setAutoscrolls(true);

        lblTimesInterestCalculatedPerYear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTimesInterestCalculatedPerYear.setText("Times Interest Calculated Per Year: ");

        lblLoanPeriod.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLoanPeriod.setText("Loan Period (years): ");

        lblVacency.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblVacency.setText("Vacency and Credit Losses Estimate (%):");

        txtPropertyInsuranceEstimate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(lblVacency)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVacency, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(lblInternalRateOfReturnIterations)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtInternalRateOfReturnIterations, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(lblLoanPeriod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLoanPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addComponent(lblPersonalIncome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPersonalIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblPersonalExpensesPer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbPersonalExpensesPeriod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                            .addComponent(lblPersonalExpenses)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPersonalExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblPersonalIncomePer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbPersonalIncomePeriod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                            .addComponent(lblTimesInterestCalculatedPerYear)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTimesInterestCalculatedPerYear, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                            .addComponent(lblPropertyInsuranceEstimate)
                            .addGap(18, 18, 18)
                            .addComponent(txtPropertyInsuranceEstimate))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                            .addComponent(lblPropertyManagementEstimate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtPropertyManagementEstimate, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoanPeriod)
                    .addComponent(txtLoanPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVacency)
                    .addComponent(txtVacency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersonalIncome)
                    .addComponent(txtPersonalIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPersonalExpensesPer)
                    .addComponent(cmbPersonalExpensesPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersonalExpenses)
                    .addComponent(txtPersonalExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPersonalIncomePer)
                    .addComponent(cmbPersonalIncomePeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimesInterestCalculatedPerYear)
                    .addComponent(txtTimesInterestCalculatedPerYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInternalRateOfReturnIterations)
                    .addComponent(txtInternalRateOfReturnIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPropertyManagementEstimate)
                    .addComponent(txtPropertyManagementEstimate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPropertyInsuranceEstimate)
                    .addComponent(txtPropertyInsuranceEstimate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fonts - Coming Soon!");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Constants");

        jPanel19.setPreferredSize(new java.awt.Dimension(218, 46));

        btnFontTabs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFontTabs.setText("FONT");

        lblFontTabs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFontTabs.setText("Tabs");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(lblFontTabs)
                .addGap(18, 18, 18)
                .addComponent(btnFontTabs)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFontTabs)
                    .addComponent(btnFontTabs))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setPreferredSize(new java.awt.Dimension(218, 46));

        lblFontButtons.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFontButtons.setText("Buttons");

        btnFontButtons.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFontButtons.setText("FONT");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(lblFontButtons)
                .addGap(18, 18, 18)
                .addComponent(btnFontButtons)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFontButtons)
                    .addComponent(btnFontButtons))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblFontInputsAndOutputs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFontInputsAndOutputs.setText("Inputs and Outputs");

        btnFontInputsAndOutputs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFontInputsAndOutputs.setText("FONT");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFontInputsAndOutputs)
                .addGap(18, 18, 18)
                .addComponent(btnFontInputsAndOutputs)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFontInputsAndOutputs)
                    .addComponent(btnFontInputsAndOutputs))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Advanced Mode");

        btnAdvanced.setBackground(new java.awt.Color(0, 255, 0));
        btnAdvanced.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdvanced.setSelected(true);
        btnAdvanced.setText("ON");
        btnAdvanced.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 0), 2, true));
        btnAdvanced.setFocusPainted(false);
        btnAdvanced.setOpaque(true);
        btnAdvanced.setRolloverEnabled(false);
        btnAdvanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdvancedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator3)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 112, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 112, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator6)))
                        .addContainerGap())))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdvanced, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdvanced, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSettingsTabLayout = new javax.swing.GroupLayout(pnlSettingsTab);
        pnlSettingsTab.setLayout(pnlSettingsTabLayout);
        pnlSettingsTabLayout.setHorizontalGroup(
            pnlSettingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlSettingsTabLayout.setVerticalGroup(
            pnlSettingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabMain.addTab("Settings", pnlSettingsTab);

        pnlStatusBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlStatusBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlStatusBar.setMaximumSize(new java.awt.Dimension(0, 32));
        pnlStatusBar.setMinimumSize(new java.awt.Dimension(0, 32));
        pnlStatusBar.setPreferredSize(new java.awt.Dimension(0, 32));

        btnApplySettings.setText("Apply Settings");
        btnApplySettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplySettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlStatusBarLayout = new javax.swing.GroupLayout(pnlStatusBar);
        pnlStatusBar.setLayout(pnlStatusBarLayout);
        pnlStatusBarLayout.setHorizontalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatusBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnApplySettings))
        );
        pnlStatusBarLayout.setVerticalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatusBarLayout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(btnApplySettings))
        );

        javax.swing.GroupLayout pnlRootLayout = new javax.swing.GroupLayout(pnlRoot);
        pnlRoot.setLayout(pnlRootLayout);
        pnlRootLayout.setHorizontalGroup(
            pnlRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE)
            .addComponent(pnlStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE)
        );
        pnlRootLayout.setVerticalGroup(
            pnlRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRootLayout.createSequentialGroup()
                .addComponent(tabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 773, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        tabMain.getAccessibleContext().setAccessibleName("Input");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // init() function handles allll of the output displays and everything.
    private void init(){
        // clear all of the persistant data. - this function is called more than once, so it is benificial to make sure everything is clean
        CustomBars.clear();
        CustomBarContainers.clear();
        CustomBarLabels.clear();
        OutputValueMetricLabels.clear();
        OutputMetrics.clear();
        // remove all objects on the output center panel remove, validate and repaint
        pnlOutputCenter.removeAll();
        pnlOutputCenter.revalidate();
        pnlOutputCenter.repaint();
        
        // calculates all of the paddings to ensure that it looks right(ish) on most displays
        OutputMetricLabelPaddingLeft = OutputLabelWidth-CustomBarWidth/2+25;
        OutputValueLabelPaddingLeft = OutputLabelWidth-CustomBarWidth/2-25;
        CustomBarPaddingLeft = (int) (OutputLabelWidth*2.5+OutputLabelPaddingLeft)-25;
        // sets the background to the signiture aspfalt color
        pnlOutputCenter.setBackground(new Color(44,62,80));
        //makes the apply settings button invisible as its not on the settings page (yet)
        btnApplySettings.setVisible(false);
        // set the default value for btnPropertyManagement and update the graphics by calling btnPropertyManagementActionPerformed
        btnPropertyManagement.setSelected(true);
        btnPropertyManagementActionPerformed(null);
        
        
        // for each text in the OutputCustomBarMetricText, make a container for the cusombars
        for (int i = 0; i < OutputCustomBarMetricText.size(); i++) {
            CustomBarContainers.add(null);
        }
        
        // for each text in the OutputCustomBarMetricText, make a label for the cusombars
        for (int i = 0; i < OutputValueMetricText.size(); i++) {
            // initialise label
            JLabel TextLabel =  new JLabel(OutputValueMetricText.get(i));
            
            // add it to the label array
            OutputValueMetricLabels.add(TextLabel);
            // set font, color, size and location based on index
            TextLabel.setFont(new Font(OutputLabelFontName, 0, OutputLabelFontSize));
            TextLabel.setSize(OutputLabelWidth, OutputValueLabelHeight);
            TextLabel.setLocation(OutputLabelPaddingLeft, CustomBarContainers.size()*(CustomBarHeight+CustomBarPaddingTop)+(i*(OutputValueLabelHeight+CustomBarPaddingTop)));
            TextLabel.setForeground(Color.WHITE);
            // add the label to the main panel
            pnlOutputCenter.add(TextLabel);
        }
        // add a variable for determining index
        int CustomBarContainersIndex = 0;
        // for every container in the CustomBarContainers:
        for (JPanel j : CustomBarContainers) {
//            increment the counter
            CustomBarContainersIndex += 1;
            // make a nwe label with the text needed
            JLabel l = new JLabel(OutputCustomBarMetricText.get(CustomBarContainersIndex-1));
            // add that label to the panel
            pnlOutputCenter.add(l);
            // set size, font and color
            l.setSize(OutputLabelWidth, CustomBarWidth);
            l.setFont(new Font(OutputLabelFontName, 0, OutputLabelFontSize));
            l.setForeground(Color.white);
            // add the label to the array
            CustomBarLabels.add(l);
            
            //create a new custombar!
            CustomBar x = new CustomBar(CustomBarWidth, CustomBarHeight, "Low", "High", 0.4f);
            // add the new control to the array of custombars
            CustomBars.add(x);
            
            // set random values so if things break, we dont get stuck with java.lang.indexOutOfBoundsException
            x.PositionPercentage = 0.01f;
            x.ModerateAreaRatio = OutputMetricSpreads.get(CustomBarContainersIndex);
            x.setLocation(0, 0);
            
            // set the location of the label based on where the custombar is
            l.setLocation(OutputLabelPaddingLeft, (CustomBarContainersIndex-4)*(x.getHeight() + CustomBarPaddingTop));
            
            // make a new panel
            j = new JPanel(new BorderLayout());
            // add it to the main panel
            pnlOutputCenter.add(j);
            
            // add the custombar to the child container
            j.add(x);
            // set its size to the same as ITS child (childcepstion)
            j.setSize(x.getWidth(), x.getHeight());
            // set its location based on the label
            j.setLocation(l.getX()+ CustomBarPaddingLeft, (l.getY())-(j.getHeight()-l.getHeight())/2);
            
            // refresh the label
            l.revalidate();
            l.repaint();
            // Adnndd and panel..
            j.revalidate();
            j.repaint();
        }
        // For each output label Metric output
        for (int i = 0; i < (OutputValueMetricText.size() + CustomBarContainers.size()); i++){
            // make a new label
            JLabel l = new JLabel("");
            // add the label at index i
            OutputMetrics.add(i, l);
            // Set its font, size color and alignment
            l.setFont(new Font(OutputLabelFontName, 0, OutputLabelFontSize-2));
            l.setSize(OutputLabelWidth, OutputValueLabelHeight);
            l.setHorizontalAlignment(SwingConstants.RIGHT);
            l.setForeground(Color.white);
            // for every label in the custombar section:
            if(i < CustomBarContainers.size()){
                //set its location
                l.setLocation(OutputLabelWidth + 2*OutputValueLabelPaddingLeft, 
                        i*(CustomBarHeight+CustomBarPaddingTop)+(CustomBarHeight-OutputValueLabelHeight)/2);
            // for every label after the custombar section:
            }else{
                // Set its location
                l.setLocation((OutputLabelWidth + 2*OutputMetricLabelPaddingLeft), 
                        (CustomBarContainers.size()*(CustomBarHeight+CustomBarPaddingTop))+((i-CustomBars.size())*(OutputValueLabelHeight+CustomBarPaddingTop)));
            }
            // either way, add the label to the main output panel
            pnlOutputCenter.add(l);
        }
        // as the pnlOutputCenter panel hasnt been added anywhere, we add it to the pnlOutput
        pnlOutput.add(pnlOutputCenter);
        // we set the center size as 70% of the width
        pnlOutputCenter.setSize((int) (pnlOutput.getWidth()*0.7), pnlOutput.getHeight());
        // set its location reletivly centered.
        pnlOutputCenter.setLocation((int) (pnlOutputCenter.getWidth()*0.3/2), 0);
        
        // for each label, add a tooltip
        for (JLabel l : CustomBarLabels) {
            // try catch in case we go over the edge.
            try {
                // set tooltip
                l.setToolTipText(Tooltips.get(CustomBarLabels.indexOf(l)));
            } catch (Exception e) {}
        }
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // when the window is first opened, we update the settings page.
        UpdateSettingsValues();
    }//GEN-LAST:event_formWindowOpened

    // when the apply button is pressed: 
    private void btnApplySettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplySettingsActionPerformed
        // all of these lines set the calculator's constants to what ever was inputted in the settings dialog.
        calculator.CPIRate = Float.valueOf(txtCPI.getText())/100;
        calculator.CostsAccounting = Float.valueOf(txtCostsAccounting.getText());
        calculator.CostsJanitorialService = Float.valueOf(txtCostsJanitorial.getText());
        calculator.CostsLegal = Float.valueOf(txtCostsLegal.getText());
        calculator.CostsOfficeSupplies = Float.valueOf(txtCostsOffice.getText());
        calculator.CostsTelephone = Float.valueOf(txtCostsPhone.getText());
        calculator.CostsRepairs = Float.valueOf(txtCostsRepair.getText())/100;
        calculator.InterestRate = Float.valueOf(txtInterestRate.getText())/100;
        calculator.InternalRateOfReturnIterations = Float.valueOf(txtInternalRateOfReturnIterations.getText());
        calculator.LoanPeriod = Integer.valueOf(txtLoanPeriod.getText());
        calculator.VacancyCreditLossesRate = Float.valueOf(txtVacency.getText())/100;
        calculator.PersonalIncome = convertUnits(cmbPersonalIncomePeriod.getSelectedIndex(), Float.valueOf(txtPersonalIncome.getText()));
        calculator.PersonalExpenses = convertUnits(cmbPersonalExpensesPeriod.getSelectedIndex(), Float.valueOf(txtPersonalExpenses.getText()));
        calculator.TimesInterestCalculatedPerYear = Integer.valueOf(txtTimesInterestCalculatedPerYear.getText());
        calculator.PropertyInsuranceEstimate = Float.valueOf(txtPropertyInsuranceEstimate.getText())/100;
        calculator.PropertyManagementEstimate = Float.valueOf(txtPropertyManagementEstimate.getText())/100;
        // update the settings with the new values.
        UpdateSettingsValues();
    }//GEN-LAST:event_btnApplySettingsActionPerformed

    private void txtDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepositActionPerformed
        
    }//GEN-LAST:event_txtDepositActionPerformed

    private void pnlInputComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlInputComponentResized
        
    }//GEN-LAST:event_pnlInputComponentResized

    // when the tab changes from one to another
    private void tabMainStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabMainStateChanged
        // if we move onto the output page:
        if(tabMain.getSelectedIndex() == 1){
            // update the outputs with the calculated values
            UpdateInputs();
            // make sure that the apply settings is invisible
            btnApplySettings.setVisible(false);
        }
        // If we are on the settings page 
        else if(tabMain.getSelectedIndex() == 2){
            // - set the apply settings to visible
            btnApplySettings.setVisible(true);
        // If we are on any other page, make it invisible
        } else {
            btnApplySettings.setVisible(false);
        }
    }//GEN-LAST:event_tabMainStateChanged

    private void txtMarketValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarketValueActionPerformed

    }//GEN-LAST:event_txtMarketValueActionPerformed

    // when the property management toggle button is pressed
    private void btnPropertyManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPropertyManagementActionPerformed
        // check if it is selected:
        if(btnPropertyManagement.isSelected()){
            // if so, set the border to a green and change the text to "YES"
            btnPropertyManagement.setBorder(new LineBorder(Color.green, 1));
            btnPropertyManagement.setText("YES");
            // to make the button functional, set the property management estimate to the default value
            calculator.PropertyManagementEstimate = 0.065f;
        }else{
            // if it isnt selected: set the border to red and change the text to "NO"
            btnPropertyManagement.setBorder(new LineBorder(Color.red, 1));
            btnPropertyManagement.setText("NO");
            // to make the button functional, set the property management estimate to 0, as the button is disabled
            calculator.PropertyManagementEstimate = 0;
        }
        // we then update the outputs to make sure the changes have been applied
        UpdateInputs();
    }//GEN-LAST:event_btnPropertyManagementActionPerformed

    private void pnlInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlInputKeyPressed
        
    }//GEN-LAST:event_pnlInputKeyPressed

    private void btnAdvancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvancedActionPerformed
        // just as the property management toggle button, we check if the button is selected.
        if(btnAdvanced.isSelected()){
            // if the button is selected, change the border to green and the text to "ON"
            btnAdvanced.setBorder(new LineBorder(Color.green, 2));
            btnAdvanced.setText("ON");
            // to make the button functional:
            // clear the array of text labels as we are changing it to somthing else
            OutputValueMetricText.clear();
            // add our new array of labels for the outputvalue metrics
            OutputValueMetricText.addAll(new ArrayList<String>(
                Arrays.asList(
                    "Cash On Cash Return", "Cash Flow", "Mortgage Repayments", "Net Operating Income", "Gross Scheduled Income", "Gross Operating Income", "Operating Expenses", "Property Taxes", "Property Insurance", "Property Management", "Maintenance and Repairs", "Landscaping", "Legal", "Accounting", "Telephone", "Office Supplies", "Cleaning Service", "Pest Control", "Advertising", ""
                )));
            // we clear the OutputCustomBarMetricText as they change as well.
            OutputCustomBarMetricText.clear();
            // next we get add our new array just as before
            OutputCustomBarMetricText.addAll(new ArrayList<String>(
            Arrays.asList(
                    "Debt Coverage Ratio", "Loan To Value Ratio", "Gross Rental Yield", "Cash Break Even Ratio", "Price To Income Ratio", "Operating Expense Ratio", "Capitalization Rate", "Internal Rate of Return", "Gross Rental Multiplier", "Return on Investment"
            )));
            // init then updates all of the graphical elements of the output screen
            init();
        // if the advanced button is not selected
        }else{
            // then change the border to red and the text to "OFF"
            btnAdvanced.setBorder(new LineBorder(Color.red, 2));
            btnAdvanced.setText("OFF");

            // clear the array
            OutputValueMetricText.clear();
            // replace the new array
            OutputValueMetricText.addAll(new ArrayList<String>(
                Arrays.asList(
                    "Cash Flow", "Mortgage Repayments", "Net Operating Income", "Operating Expenses", "Property Taxes", "Property Insurance", "Property Management", "Maintenance and Repairs", "Landscaping", "Legal", "Accounting", "Telephone", "Office Supplies", "Cleaning Service", "Pest Control", "Advertising", ""
                )));
            // clear the other array
            OutputCustomBarMetricText.clear();
            // add the new text values to the array
            OutputCustomBarMetricText.addAll(new ArrayList<String>(
                Arrays.asList(
                    "Gross Rental Yield", "Capitalization Rate", "Return on Investment"
                )));
            
            init();
            // init then updates all of the graphical elements of the output screen
        }
    }//GEN-LAST:event_btnAdvancedActionPerformed

    private void txtYearBuiltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearBuiltActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearBuiltActionPerformed

    // updates the inputs and gives them to the outputs
    public void UpdateInputs() {
        // because the text might be red (see below), change the input labels text to white
        lblMarketValue.setForeground(Color.white);
        lblDeposit.setForeground(Color.white);
        lblPropertyIncome.setForeground(Color.white);
        lblPropertyManagement.setForeground(Color.white);
        lblYearBuilt.setForeground(Color.white);
        
        // if we are on the output page:
        if(tabMain.getSelectedIndex() == 1){
            // try catch in case the input produces an error.
            try {
                // try to set the market value from the input page
                calculator.MarketValue = Float.valueOf(txtMarketValue.getText());
            // catch - set the index back to the input page
            } catch (Exception e) {tabMain.setSelectedIndex(0);
                // if we fail, set the color to red to notify user
                lblMarketValue.setForeground(Color.RED);
            // try catch in case the input produces an error.
            }try {
                // try to set the deposit from the input page, if its percentage /100 to make sure that the percentage is usable in calculations
                    // if in dollar mode, / by market value to convert the number into a percentage.
                calculator.Deposit = cmbDepositUnit.getSelectedIndex() == 0 ? Float.valueOf(txtDeposit.getText())/100 : Float.valueOf(txtDeposit.getText())/calculator.MarketValue;
                // try to set the startupcapital (not used) from the input page, if its percentage /100 and * by market value to make sure that it is a number
                    // if in dollar mode, set the startupcapital.
                calculator.StartupCapital = cmbDepositUnit.getSelectedIndex() == 0 ? (Float.valueOf(txtDeposit.getText())/100)*calculator.MarketValue : Float.valueOf(txtDeposit.getText());
                
            // catch - set the index back to the input page
            } catch (Exception e) {tabMain.setSelectedIndex(0);
                // if we fail, set the color to red to notify user
                lblDeposit.setForeground(Color.RED);
            }
            // try catch in case the input produces an error.
            try {
                // try to set the property income using the convertUnits() function (see below) to make sure it is in the correct units based on the input of the combobox
                calculator.PropertyIncome = Float.valueOf(convertUnits(cmbPropertyIncomePeriod.getSelectedIndex(), Float.valueOf(txtPropertyIncome.getText())));
                
            // catch - set the index back to the input page
            } catch (Exception e) {tabMain.setSelectedIndex(0);
            // catch - set the index back to the input page
                // if it fails, we set the label to red to notify user
                lblPropertyIncome.setForeground(Color.RED);
            // try catch in case the input produces an error.
            }try {
                // this is only used to calculate depreciatin, but we still set it.
                calculator.YearBuilt = Integer.valueOf(txtYearBuilt.getText());
            // catch - set the index back to the input page
            } catch (Exception e) {tabMain.setSelectedIndex(0);
                // if this fails, we set the label to red
                lblYearBuilt.setForeground(Color.RED);
            }
            // preset the values based on the newly inputed inputs. vacancy and repairs - these are percentages but they need to be values
            calculator.VacancyCreditLosses = calculator.VacancyCreditLossesRate*calculator.CalculateGrossScheduledIncome();
            calculator.CostsRepairs = calculator.RepairsRate*calculator.MarketValue;
        }
        // yet another try catch block: this time, it is to make sure that if we are not in advanced mode, it doesnt crash due to java.lang.IndexOutOfBoundsException
        try {
            // check if we are on the output page
            if(tabMain.getSelectedIndex()==1){
                // if the OutputValueMetricLabels isnt initilised:
                if(OutputValueMetricLabels.size() > 0){
                    /// the advantage of searching an array for a label text is that if the array doesnt contain the entry it will skip it as it cannot find it.
                    /// in this case, it is important to use this method as there is an advanced mode, which has more enteries in the array.
                    /// another advantage is that these lines of code dont need to be in order. easier to program and more efficient
                    // all of these lines set the appropriate output based on the text as the first parameter of SetCustomBarValue, the format decimal returns a float with the last few digits rounded from the calculated outputs using calculator.CalculateMeric();
                    SetCustomBarValue("Debt Coverage Ratio", formatDecimal((calculator.CalculateDebtCoverageRatio())));
                    SetCustomBarValue("Loan To Value Ratio", formatDecimal((calculator.CalculateLoanToValueRatio())));
                    SetCustomBarValue("Cash Break Even Ratio", formatPercentage((calculator.CalculateCashBreakEvenRatio())));
                    SetCustomBarValue("Price To Income Ratio", formatDecimal((calculator.CalculatePriceToIncomeRatio())));
                    SetCustomBarValue("Operating Expense Ratio", formatDecimal((calculator.CalculateOperatingExpenseRatio())));
                    SetCustomBarValue("Capitalization Rate", formatPercentage((calculator.CalculateCapRate())));
                    SetCustomBarValue("Internal Rate of Return", formatPercentage((calculator.CalculateIRR())));
                    SetCustomBarValue("Gross Rental Multiplier", formatDecimal((calculator.CalculateGrossRentalMultiplier())));
                    SetCustomBarValue("Gross Rental Yield", formatPercentage((calculator.CalculateGrossRentalYield())));
                    SetCustomBarValue("Return on Investment", formatPercentage((calculator.CalculateReturnOnInvestment())));
                    // Set pointer Position, simmilar to SetCustomBarValue, however finds the output from the label (1st parameter) then the value in question (2nd parameter), then uses a custom normalisation alrorithm to scale the data into a standard distribution based on the border between an acceptable low and acceptable high
                    SetPointerPosition("Debt Coverage Ratio", calculator.CalculateDebtCoverageRatio(), 1, 1.35);
                    SetPointerPosition("Loan To Value Ratio", calculator.CalculateLoanToValueRatio(), 0.86, 0.5);
                    SetPointerPosition("Cash Break Even Ratio", calculator.CalculateCashBreakEvenRatio(), 0.85, 0.5);
                    SetPointerPosition("Price To Income Ratio", calculator.CalculatePriceToIncomeRatio(), 27, 12.5);
                    SetPointerPosition("Operating Expense Ratio", calculator.CalculateOperatingExpenseRatio(), 0.6, 0.18);
                    SetPointerPosition("Capitalization Rate", calculator.CalculateCapRate(), 0.04, 0.1);
                    SetPointerPosition("Internal Rate of Return", calculator.CalculateIRR(), -0.1, 0.15);
                    SetPointerPosition("Gross Rental Multiplier", calculator.CalculateGrossRentalMultiplier(), 5.5, 2.75);
                    SetPointerPosition("Gross Rental Yield", calculator.CalculateGrossRentalYield(), 0.05, 0.13);
                    SetPointerPosition("Return on Investment", calculator.CalculateReturnOnInvestment(), 0.075, 0.12);
                    // sets the values at the bottom of the screen - mostly the monetary outputs.
                    SetMetricValue("Cash Flow", formatCurrency(calculator.CalculateCashFlowBeforeTax()), 1);
                    SetMetricValue("Cash On Cash Return", formatPercentage(calculator.CalculateCashOnCashReturn()), -1);
                    SetMetricValue("Mortgage Repayments", formatCurrency(calculator.CalculateLoanRepayments()), 1);
                    SetMetricValue("Net Operating Income", formatCurrency(calculator.CalculateAnnualNOI()), 2);
                    SetMetricValue("Gross Scheduled Income", formatCurrency(calculator.CalculateGrossScheduledIncome()), 2);
                    SetMetricValue("Gross Operating Income", formatCurrency(calculator.CalculateGrossOperatingIncome()), 2);
                    SetMetricValue("Operating Expenses", formatCurrency(calculator.CalculateOperatingExpenses()), 2);
                    SetMetricValue("Property Taxes", formatCurrency(calculator.CalculateLandTax()), 2);
                    SetMetricValue("Property Insurance", formatCurrency(calculator.CalculatePropertyInsurance()), 2);
                    SetMetricValue("Property Management", formatCurrency(calculator.CalculatePropertyManagement()), 2);
                    SetMetricValue("Maintenance and Repairs", formatCurrency(calculator.CostsRepairs), 2);
                    SetMetricValue("Landscaping", formatCurrency(calculator.CostsLandscaping), 2);
                    SetMetricValue("Legal", formatCurrency(calculator.CostsLegal), 2);
                    SetMetricValue("Accounting", formatCurrency(calculator.CostsAccounting), 2);
                    SetMetricValue("Telephone", formatCurrency(calculator.CostsTelephone), 2);
                    SetMetricValue("Office Supplies", formatCurrency(calculator.CostsOfficeSupplies), 2);
                    SetMetricValue("Cleaning Service", formatCurrency(calculator.CostsJanitorialService), 2);
                    SetMetricValue("Pest Control", formatCurrency(calculator.CostsPestControl), 2);
                    SetMetricValue("Advertising", formatCurrency(calculator.CostsAdvertising), 2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // This function turns the index of the input (Week (0), Fortnight (1), Month(2), Quarter(3), Year(3))
    // Into the standard yearly unit.
    private float convertUnits(int index, float value){
        // converts all of the units to per year
        switch(index){
            case 0: // Week
                return value*52;
            case 1: // Fortnight
                return value*26;
            case 2: // Month
                return value*12;
            case 3: // Quarter
                return value*4;
            case 4: // Year
                return value;
            default:
                return value;
        }
    }
    // this function sets the pointer position based on the findPointerPosition function.
    public void SetPointerPosition(String name, float value, double min, double max){
        // try catch as if we are in non-advanced mode, the array will be shorter than expected so we only catch the ArrayIndexOutOfBoundsException, any other will crash the program safely
        try{
            // get the custombar that relates to the String name parameter, then use the .SetPositionPercentage() function to set it using the format decimal function given the name, value, min, and max values
            CustomBars.get(OutputCustomBarMetricText.indexOf(name)).
                SetPositionPercentage(Float.valueOf(formatDecimal(findPointerPosition(name, value, min, max))));
        }catch (ArrayIndexOutOfBoundsException ex){}
    }
    // UpdateSettingsValues() sets the right values for the settings
    private void UpdateSettingsValues() {
        // These lines set the values of the settings to the calculator's values
        txtCPI.setText(String.valueOf(calculator.CPIRate*100));
        txtCostsAccounting.setText(String.valueOf(calculator.CostsAccounting));
        txtCostsJanitorial.setText(String.valueOf(calculator.CostsJanitorialService));
        txtCostsLegal.setText(String.valueOf(calculator.CostsLegal));
        txtCostsOffice.setText(String.valueOf(calculator.CostsOfficeSupplies));
        txtCostsPhone.setText(String.valueOf(calculator.CostsTelephone));
        txtCostsRepair.setText(String.valueOf(calculator.CostsRepairs*100));
        txtInterestRate.setText(String.valueOf(calculator.InterestRate*100));
        txtInternalRateOfReturnIterations.setText(String.valueOf(calculator.InternalRateOfReturnIterations));
        txtLoanPeriod.setText(String.valueOf(calculator.LoanPeriod));
        txtVacency.setText(String.valueOf(calculator.VacancyCreditLossesRate*100));
        txtPersonalIncome.setText(String.valueOf(calculator.PersonalIncome));
        cmbPersonalIncomePeriod.setSelectedIndex(4);
        txtPersonalExpenses.setText(String.valueOf(calculator.PersonalExpenses));
        cmbPersonalExpensesPeriod.setSelectedIndex(4);
        txtTimesInterestCalculatedPerYear.setText(String.valueOf(calculator.TimesInterestCalculatedPerYear));
        txtPropertyInsuranceEstimate.setText(String.valueOf(calculator.PropertyInsuranceEstimate*100));
        txtPropertyManagementEstimate.setText(String.valueOf(calculator.PropertyManagementEstimate*100));
    }
    // this function sets primarily the monetary values (lower section)
    public void SetMetricValue(String name, String formattedValue, int time){
        // init unit string
        String unit;
        // changes what unit it is using based on the time parameter
        switch(time){
            case 0: // 0 = per week
                unit = "    Per Week";
                break;
            case 1: // 1 = per month
                unit = "   Per Month";
                break;
            case 2: // 2 = per year
                unit = "    Per Year";
                break;
            default: // anything else is the same as the input
                unit = "             ";
        }
        // try catch for the same reason above, this makes sure that if wed arent in advanced mode, the program doesnt crash due to java.lang.IndexOutOfBoundsException
        try{
            // gets the outputmetric based on the parameter name then sets the text to the string formattedValue + the unit.
            OutputMetrics.get(OutputValueMetricText.indexOf(name)+CustomBars.size()).setText(
                formattedValue + unit);
        }catch (ArrayIndexOutOfBoundsException ex){}
        // Parameter 'time' is the period of time that the metric is based on.
        //      0 = week;    1 = month;  2 = year;  -1 = no Unit
    }
    public void SetCustomBarValue(String name, String formattedValue){
        // try-catch in case there is an java.lang.IndexOutOfBoundsException.
        try{
            // get the outputmetric based on the name label value and set it to the formatted value passed into the function
            OutputMetrics.get(OutputCustomBarMetricText.indexOf(name)).setText(
            formattedValue);
        // catch java.lang.IndexOutOfBoundsException.
        }catch (ArrayIndexOutOfBoundsException ex){}
    }
    
    // findPointerPosition() finds the pointer position based on the lows and highs passed in.
    private float findPointerPosition(String name, float value, double acceptableLow, double acceptableHigh){
        // gets the ModSpread from the calculator (the percentage that the yellow section covers on the screen)
        double ModSpread = CustomBars.get(OutputCustomBarMetricText.indexOf(name)).ModerateAreaRatio;
        // the min and max values are determend using a custom algorithm, used to scale them from acceptable to absolute max or min    
        float min = (float) (acceptableLow-((1-ModSpread)/2)*acceptableLow);
        float max = (float) (acceptableHigh+((1-ModSpread)/2)*acceptableHigh);
        // determins the normalised value (between 1 and 0) using the standard normalisation standardisation algorithm
        double normalised = (value-min)/(Math.abs(max-min));
        // if the normalised is really high just set it to the highest visible poisition
        if(normalised > 0.99)
            normalised = 0.99f;
        // if the normalised is really low just set it to the lowest visible poisition
        else if(normalised < 0.01f)
            normalised = 0.01f;
        
        // if the value is inverted (the heigher the value, the worse the output) determined if the low is greater than the high
        if(acceptableLow > acceptableHigh){
            // make the normalised = 1-normalised (eg. 0.9 -> 0.1)
            normalised = 1-normalised;
        }
        // return the final normalised value
        return (float) (normalised);
    }
    // formats currencys 
    public static String formatCurrency(float value){
        // creates a new instance of a NumberFormat
        NumberFormat format = NumberFormat.getCurrencyInstance();
        // sets the rounding parameters
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.setMaximumFractionDigits(2);
        // returns the formatted currency using the number formatter
        return (format.format(value));
    }
    // formats a percentage
    public static String formatPercentage(float value){
        // creates a new instance of a NumberFormat
        NumberFormat format = NumberFormat.getPercentInstance();
        // sets the rounding parameters
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.setMinimumFractionDigits(3);
        // returns the formatted percentage using the number formatter
        return (format.format(value));
    }
    
    public static String formatDecimal(float value){
        // creates a new instance of a NumberFormat
        NumberFormat format = NumberFormat.getNumberInstance();
        // sets the rounding parameters
        format.setRoundingMode(RoundingMode.HALF_UP);
        format.setMinimumFractionDigits(3);
        // returns the formatted decimal using the number formatter
        return format.format(value);
    }
    
// Main Class
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JToggleButton btnAdvanced;
    public javax.swing.JButton btnApplySettings;
    public javax.swing.JButton btnFontButtons;
    public javax.swing.JButton btnFontInputsAndOutputs;
    public javax.swing.JButton btnFontTabs;
    public javax.swing.JToggleButton btnPropertyManagement;
    public javax.swing.JComboBox<String> cmbDepositUnit;
    public javax.swing.JComboBox<String> cmbPersonalExpensesPeriod;
    public javax.swing.JComboBox<String> cmbPersonalIncomePeriod;
    public javax.swing.JComboBox<String> cmbPropertyIncomePeriod;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanel13;
    public javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel15;
    public javax.swing.JPanel jPanel18;
    public javax.swing.JPanel jPanel19;
    public javax.swing.JPanel jPanel20;
    public javax.swing.JPanel jPanel21;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JSeparator jSeparator4;
    public javax.swing.JSeparator jSeparator6;
    public javax.swing.JLabel lblCPI;
    public javax.swing.JLabel lblCostsAccounting;
    public javax.swing.JLabel lblCostsJanitorial;
    public javax.swing.JLabel lblCostsLegal;
    public javax.swing.JLabel lblCostsOffice;
    public javax.swing.JLabel lblCostsPhone;
    public javax.swing.JLabel lblCostsRepair;
    public javax.swing.JLabel lblDeposit;
    public javax.swing.JLabel lblFontButtons;
    public javax.swing.JLabel lblFontInputsAndOutputs;
    public javax.swing.JLabel lblFontTabs;
    public javax.swing.JLabel lblInputImage;
    public javax.swing.JLabel lblInterestRate;
    public javax.swing.JLabel lblInternalRateOfReturnIterations;
    public javax.swing.JLabel lblLoanPeriod;
    public javax.swing.JLabel lblMarketValue;
    public javax.swing.JLabel lblPersonalExpenses;
    public javax.swing.JLabel lblPersonalExpensesPer;
    public javax.swing.JLabel lblPersonalIncome;
    public javax.swing.JLabel lblPersonalIncomePer;
    public javax.swing.JLabel lblPropertyIncome;
    public javax.swing.JLabel lblPropertyIncomePer;
    public javax.swing.JLabel lblPropertyInsuranceEstimate;
    public javax.swing.JLabel lblPropertyManagement;
    public javax.swing.JLabel lblPropertyManagementEstimate;
    public javax.swing.JLabel lblTimesInterestCalculatedPerYear;
    public javax.swing.JLabel lblTitle;
    public javax.swing.JLabel lblVacency;
    public javax.swing.JLabel lblYearBuilt;
    public javax.swing.JPanel pnlHeading;
    public javax.swing.JPanel pnlInput;
    public javax.swing.JPanel pnlInputLeft;
    public javax.swing.JPanel pnlInputRight;
    public javax.swing.JPanel pnlInputTab;
    public javax.swing.JPanel pnlOutput;
    public javax.swing.JPanel pnlOutputTab;
    public javax.swing.JPanel pnlRoot;
    public javax.swing.JPanel pnlSettingsTab;
    public javax.swing.JPanel pnlStatusBar;
    public javax.swing.JScrollPane scrOutput;
    public javax.swing.JTabbedPane tabMain;
    public javax.swing.JTextField txtCPI;
    public javax.swing.JTextField txtCostsAccounting;
    public javax.swing.JTextField txtCostsJanitorial;
    public javax.swing.JTextField txtCostsLegal;
    public javax.swing.JTextField txtCostsOffice;
    public javax.swing.JTextField txtCostsPhone;
    public javax.swing.JTextField txtCostsRepair;
    public javax.swing.JTextField txtDeposit;
    public javax.swing.JTextField txtInterestRate;
    public javax.swing.JTextField txtInternalRateOfReturnIterations;
    public javax.swing.JTextField txtLoanPeriod;
    public javax.swing.JTextField txtMarketValue;
    public javax.swing.JTextField txtPersonalExpenses;
    public javax.swing.JTextField txtPersonalIncome;
    public javax.swing.JTextField txtPropertyIncome;
    public javax.swing.JTextField txtPropertyInsuranceEstimate;
    public javax.swing.JTextField txtPropertyManagementEstimate;
    public javax.swing.JTextField txtTimesInterestCalculatedPerYear;
    public javax.swing.JTextField txtVacency;
    public javax.swing.JTextField txtYearBuilt;
    // End of variables declaration//GEN-END:variables

}
