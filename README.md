# E-Bond-Trader
 Construct an E-Bond trading application, to allow traders to manage different bonds, book trades and view the booked trades.  
 The core components should include the following:  
· Reference data on available bonds  
· Technical Analysis Algorithms  
· Transactional business logic to record trades  
· Suitable database persistence of trade history  
· Interactive client implemented as either a desktop client or a browser-based web client  
The functional requirements are listed below  
Requirements - The application should contain the following 3 screens  
Bond Static Maintenance A user interface screen with the fields outlined below  
a. ISIN Bond unique identifier  
b. Description Bond description  
c. Start Date Start date of the bond  
d. Maturity Date Maturity date of the bond  
e. Period Coupon period (Quarterly; Half Yearly;  
Annually)
f. Coupon Rate Interest Rate  
g. Piece Size Unit amount for 1 piece (Normally  
maintained as 100 rs)  
h. Currency Bond Currency (Drop Down)  
Trade Booking Screen  
With the fields listed below to capture new trades.  
a. Bond ISIN (Dropdown with all the maintained bonds)  
b. Settlement Date  
c. Clean Price  
d. Dirty Price  
e. Trade Yield  
f. Accrued Amount (Read Only field)  
g. Settlement Amount (Read Only field)  
Once the trader inputs bond, trade date, settle date and yield, the  
application should provide clean price, dirty price, accrued and the  
settled amount.  
On changing clean price, the dirty price and yield should be recomputed  
and accordingly the amounts.  
On changing dirty price, the clean price and yield should be recomputed  
and accordingly the amounts.  
Trade Blotter Screen All booked trades should be displayed in the blotter.  

