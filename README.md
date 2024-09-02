<h1>Crypto Exchange System</h1>
<p>
  This system contains a main menu to start with the options "Register" and "Login", if the user is new to the system the user needs to register and then log in. Once the user registers, the service (UserService) of this object generates an id and saves it in a Map and print a confirmation message <b>>> Successful register <<</b> same to login the user will be able to see <b>>> Successful login <<</b>
</p>
<p>
  Once logged in, the user will see a system menu with the options “Deposit Money”, “View Wallet Balance”, “Buy Cryptocurrencies from the Exchange”, “Place Buy Order”, “Place Sell Order”, “View Transaction History” and “Log Out”. Each of these options allows the user to interact in the system.
</p>
<ul>
  <li>Deposit Money: The system update the user's wallet balance with the amount that the user enters and print a confirmation message with the actual balance in the wallet.</li>
  <li>View Wallet Balance: The system display the fiat money and the amount of cryptocurrencies in the wallet.</li>
  <li>Buy Cryptocurrencies from the Exchange: The system present its available cryptocurrencies with their price, the user must enter the cryptocurrency and the amount, finally print a confirmation message. Cryptocurrency price fluctuates every 10 seconds.</li>
  <li>Place Buy Order: The user can generate a buy order typing the crypto, the amount and the maximum price to pay, the system verifies each one of the sell orders and if they match, it processes these orders.</li>
  <li>Place Sell Order: The user can generate a sell order by typing the crypto, the amount and the minimum value to accept, the system verifies each one of the buy orders and if they match, it processes these orders.</li>
  <li>View Transaction History: The system display the user's transactions (processed orders) with their data such as crypto, amount, price and action (buy or sell).</li>
  <li>Log Out: Log out the user.</li>
</ul>
<p>
  <b>Important: </b>
  To buy on the exchange and generate buy orders the user must have enough fiat money (if the user has generated a buy order before and it has not been processed, the maximum price to pay is still in the wallet but cannot be used for other buys). To sell cryptocurrencies the user must have enough cryptocurrencies (if the user has generated a sell order before and it has not been processed, the amount of cryptocurrencies is still in the wallet but cannot be used for other sells).
</p>
<p>
  <b>Additional information: </b>
  Once the buy and sell orders are generated they are added to a LinkedHashMap to maintain the order in which the orders are added.
</p>
<h2>Architecture pattern: MVC Model View Controller</h2>
<p>
  The model contains each of the objects related to the system such as Crypto, User, Order; the view contains how the information is presented to the user; and the controller contains the methods that manipulate the model acting as an intermediary. 
</p>
<h2>UML Class Diagram:</h2>
<img src="https://github.com/user-attachments/assets/b99ee23b-4e06-468e-8e5f-2270e7f514c4" align="center"/>
