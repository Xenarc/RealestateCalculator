
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

// Calculator Class

public class Calculator {
    public float CostsLegal = 300;
    public float CostsAccounting = 400;
    public float CostsTelephone = 300;
    public float CostsOfficeSupplies = 100;
    public float CostsJanitorialService = 100;
    public float CostsRepairs = 0.01f;
    
    public float InterestRate = 0.065f;
    public int LoanPeriod = 30;
    public String Location = "Vic";
    public float MarketValue;
    public float VacancyCreditLossesRate = 0.06f;
    public float VacancyCreditLosses;
    public float Deposit;
    public float PersonalIncome = 65000f;
    public float PersonalExpenses = 35000f;
    public int TimesInterestCalculatedPerYear = 12;
    public float PropertyIncome;
    public float PropertyManagementEstimate = 0.065f;
    public float PropertyInsuranceEstimate = 0.075f;
    public float CostsPestControl = 100.0f;
    public float CostsAdvertising = 250.0f;
    public float CostsLandscaping = 100.0f;
    public int YearBuilt;
    public float StartupCapital;
    public float RepairsRate = 0.01f;
    private ArrayList<Float> cashFlows = new ArrayList<Float>();
    public float CPIRate = 0.03f;
    public float InternalRateOfReturnIterations = 4;

    public Calculator(){
        
    }
    
    // Only called to update the repairs costs, the other costs based on a percent are elsewhere
    public void calculate() {
        // Repairs = 1% of property value
        CostsRepairs = 0.01f*MarketValue;
    }
    
    // Calculates the land tax obligation based on the state and property value
    public float CalculateLandTax(){
        // Initilisation of basic variables needed for tax calculation
        float NswLandTax = 0.016f;
        int NswLandTaxThreshold = 629000;
        int NswPremiumLandTaxThreshold = 3846000;

        float VicLandTaxBracket1Rate = 0.002f;
        int VicLandTaxBracket1Amount = 275;
        float VicLandTaxBracket2Rate = 0.005f;
        int VicLandTaxBracket2Amount = 975;
        float VicLandTaxBracket3Rate = 0.008f;
        int VicLandTaxBracket3Amount = 2975;
        float VicLandTaxBracket4Rate = 0.013f;
        int VicLandTaxBracket4Amount = 9375;
        float VicLandTaxBracket5Rate = 0.0225f;
        int VicLandTaxBracket5Amount = 24975;

        float QldLandTaxBracket1Rate = 0.01f;
        int QldLandTaxBracket1Amount = 500;
        float QldLandTaxBracket2Rate = 0.0165f;
        int QldLandTaxBracket2Amount = 4500;
        float QldLandTaxBracket3Rate = 0.0125f;
        int QldLandTaxBracket3Amount = 37500;
        float QldLandTaxBracket4Rate = 0.0175f;
        int QldLandTaxBracket4Amount = 62500;
        
        float CurrentMarketValue = MarketValue;
        // If in New South Wales - calculate based on laws in that state
        if(null != Location) switch (Location) {
            case "Nsw":
                if (CurrentMarketValue > NswPremiumLandTaxThreshold) {
                    return (float) ((((NswPremiumLandTaxThreshold - NswLandTaxThreshold)*NswLandTax) + 100) + ((CurrentMarketValue - NswPremiumLandTaxThreshold)*0.02));
                } else if(CurrentMarketValue > NswLandTaxThreshold) {
                    return (((CurrentMarketValue - NswLandTaxThreshold)*NswLandTax) + 100);
                }   break;
            // If in Victoria - calculate based on laws in that state
            case "Vic":
                if (CurrentMarketValue < 250000) {
                    return 0;
                } else if (CurrentMarketValue < 600000) {
                    return VicLandTaxBracket1Amount + (CurrentMarketValue * VicLandTaxBracket1Rate);
                } else if (CurrentMarketValue < 1000000) {
                    return VicLandTaxBracket2Amount + (CurrentMarketValue * VicLandTaxBracket2Rate);
                } else if (CurrentMarketValue < 1800000) {
                    return VicLandTaxBracket3Amount + (CurrentMarketValue * VicLandTaxBracket3Rate);
                } else if (CurrentMarketValue < 3000000) {
                    return VicLandTaxBracket4Amount + (CurrentMarketValue * VicLandTaxBracket4Rate);
                } else if (CurrentMarketValue >= 3000000) {
                    return VicLandTaxBracket5Amount + (CurrentMarketValue * VicLandTaxBracket5Rate);
                }   break;
            // If in Queensland - calculate based on laws in that state
            case "Qld":
                if (CurrentMarketValue < 600000) {
                    return 0;
                } else if (CurrentMarketValue < 1000000) {
                    return QldLandTaxBracket1Amount + (CurrentMarketValue * QldLandTaxBracket1Rate);
                } else if (CurrentMarketValue < 3000000) {
                    return QldLandTaxBracket2Amount + (CurrentMarketValue * QldLandTaxBracket2Rate);
                } else if (CurrentMarketValue < 5000000) {
                    return QldLandTaxBracket3Amount + (CurrentMarketValue * QldLandTaxBracket3Rate);
                } else if (CurrentMarketValue >= 5000000) {
                    return QldLandTaxBracket4Amount + (CurrentMarketValue * QldLandTaxBracket4Rate);
                }   break;
            default:
                break;
        }
        // If the amound doesn't reach the threashold, there is no tax, return 0.
        return 0.0f;
    }
    // Calculates the Capitalisation rate
    public float CalculateCapRate(){
        return CalculateAnnualNOI()/ MarketValue;
    }
    // Calculates the max borrowing Power (not used yet)
    public float CalculateMaxBorrowingPower(){
        return (float) ((PersonalIncome/0.35)/(InterestRate/(1-(Math.pow(1+(InterestRate/1200), (LoanPeriod*12*(-1)))))));
    }
    // Calculates the nois
    public float CalculateAnnualNOI (){
        return CalculateGrossOperatingIncome()-VacancyCreditLosses-CalculateOperatingExpenses();
    }
    // Log function for ease of debugging
    private void log(Object s){
        System.out.println("REALSESTATE: " + String.valueOf(s));
    }
    // Calculates the loan amount based on the deposit and marketvalue
    public float CalculateLoanAmount(){
        return MarketValue-(Deposit*MarketValue);
    }
    // Calculates the loan repayments throgh A=(P+Rnx)(1+R)^n
    public float CalculateLoanRepayments(){
        // Find Monthly Interest Rate
        float rate = InterestRate;
        
        rate = (rate)/TimesInterestCalculatedPerYear;
 
        float NumPayments = LoanPeriod * TimesInterestCalculatedPerYear;
        return (float)((CalculateLoanAmount() * rate) / (1 - Math.pow(1 + rate, -NumPayments)));
    }
    // Calculates the GSI
    public float CalculateGrossScheduledIncome(){
        return PropertyIncome;
    }
    // Calculates the GOI
    public float CalculateGrossOperatingIncome(){
        return CalculateGrossScheduledIncome()-VacancyCreditLosses;
    }
    // Calculates the average interest Repayments over the life of the loan
    public float CalculateAverageInterestPayments(){
        return (CalculateLoanRepayments() * LoanPeriod * TimesInterestCalculatedPerYear)-CalculateLoanAmount();
    }
    // Calculates the tax deductions
    public float CalculateDeductions(){
        return CostsAccounting+CostsAdvertising+CostsJanitorialService+CostsLandscaping+CostsLegal+CostsOfficeSupplies+CostsPestControl+CostsRepairs+CostsTelephone+CalculatePropertyInsurance()+CalculatePropertyManagement()+CalculateLandTax()+CalculateAverageInterestPayments();
    }
    // Calculates the ROI after tax
    public float CalculateReturnOnInvestment (){
        return CalculateCashFlowAfterTax()/(Deposit*MarketValue);
    }
    // Calculates the Internal Rate of Return
    public float CalculateIRR (){
        int Years = (int) InternalRateOfReturnIterations;
        if(cashFlows.size() <= 1){
            cashFlows.add(0, -Deposit*MarketValue); // The first cost is you buying the property
            cashFlows.add(1, CalculateCashFlowAfterTax());
            for (int i = 2; i < Years; i++) {
                cashFlows.add(i, cashFlows.get(i-1)+cashFlows.get(i-1)*CPIRate);
            }
        }
        
        float total = 0;
        for (int i = 1; i < cashFlows.size(); i++) {
            total += cashFlows.get(i);
        }
        
        return (cashFlows.get(0) + total)/(-cashFlows.get(0));
    }
        // Calculates the Price to debt ratio (not used)
    public float CalculatePriceToDebtRatio(){
        return PersonalIncome / CalculateLoanRepayments();
    }
    // Calculates the GRY
    public float CalculateGrossRentalYield (){
        return CalculateGrossScheduledIncome() / MarketValue;
    }
    // Calculates the annual CoC Return
    public float CalculateCashOnCashReturn () {
        return (CalculateCashFlowBeforeTax()*12)/(MarketValue-CalculateLoanAmount());
    }
    // Calculates the GRM
    public float CalculateGrossRentalMultiplier (){
        return MarketValue / CalculateGrossOperatingIncome();
    }
    // Calculates the DCR
    public float CalculateDebtCoverageRatio (){
        return CalculateAnnualNOI()/12 / CalculateLoanRepayments();
    }
    // Calculates the LVR
    public float CalculateLoanToValueRatio (){
        return CalculateLoanAmount() / MarketValue;
    }
    // Calculates the CBE Ratio
    public float CalculateCashBreakEvenRatio (){
        return (float) ((CalculateOperatingExpenses() + CalculateLoanRepayments()*12 - (0.01*MarketValue)) / CalculateGrossOperatingIncome());
    }
    // Calculates the OE Ratio
    public float CalculateOperatingExpenseRatio (){
        return CalculateOperatingExpenses() / CalculateGrossOperatingIncome();
    }
    // Calculates the operating expenses (tax, insurance management and costs)
    public float CalculateOperatingExpenses (){
        float PropertyTaxes = CalculateLandTax();
        float PropertyInsurance = PropertyInsuranceEstimate * CalculateGrossOperatingIncome();
        float PropertyManagement = PropertyManagementEstimate * CalculateGrossOperatingIncome();
        
        return PropertyTaxes + PropertyInsurance + PropertyManagement + CostsLegal + CostsAccounting + CostsTelephone + CostsOfficeSupplies + CostsJanitorialService + CostsRepairs;
    }
    // Calculates the NOI after tax
    private float CalculateNetOperatingIncome() {
        return (CalculateGrossOperatingIncome()-CalculateOperatingExpenses())
                + CalculateDepreciation()
                + CalculateInterestExpense();
    }
    // Calculates the Depreaciation of the property over time, for use for calculateDeductions()
    public float CalculateDepreciation() {
        int propertyAge = Calendar.getInstance().get(Calendar.YEAR)-YearBuilt;
        if(propertyAge < 40){
            return (float)(propertyAge)*0.025f*MarketValue;
        }else{
            return (float)40*0.025f*MarketValue;
        }
        
    }
    // Calculates the IE Ratio
    public float CalculateInterestExpense(){
        return (1+(InterestRate / TimesInterestCalculatedPerYear))*(TimesInterestCalculatedPerYear*LoanPeriod);
    }
    // Calculates the Property management cost
    public float CalculatePropertyManagement() {
        return PropertyManagementEstimate*CalculateGrossOperatingIncome();
    }
    // Calculates the property insurance
    public float CalculatePropertyInsurance() {
        return PropertyInsuranceEstimate*CalculateGrossOperatingIncome();
    }
    // Calculates the CFBT
    public float CalculateCashFlowBeforeTax() {
        return (CalculateGrossOperatingIncome() - (CalculateLoanRepayments()*12 + CalculateOperatingExpenses()))/12;
    }
    // Calculates the CFAT
    public float CalculateCashFlowAfterTax(){
        return (CalculateGrossOperatingIncome() - (CalculateLoanRepayments()*12 + CalculateOperatingExpenses())+CalculateDeductions())/12;
    }
    // Calculates the rental growth based on CPI Rate
    float CalculateRentalGrowth() {
        return CalculateAnnualNOI()/CPIRate;
    }
    // Calculates the P/E 
    float CalculatePriceToIncomeRatio() {
        return MarketValue/CalculateAnnualNOI();
    }
}

