# Shop Wisely Application

Shop Wisely is an application for cosmetic companies in Bosnia and Herzegovina (CM, DM, etc.), that saves users time going to the different markets looking for the best prices for themselves. Users of this application search and compare prices between same products, but different companies. 

## Features:

### 1. Provided features:

#### 1.1. User:
1.1.1. Create an account. <br>
1.1.2. Log in with manually created account or with facebook. <br>
1.1.3. See new products on the home page that just arrived, under 'New Arrivals'.

#### 1.2. Company:
1.2.1. Has their own admin account. <br>
1.2.2. Admin can add new items to the store. <br>
1.2.3. Adding new items, admin can provide all the information (name, price, brand, category, etc.)

### 2. Features that need to be provided:
#### 1.1. User
1.1.1. Keep track of the price updates by adding a product to list of interests. <br>
1.1.2. Buy products online. <br>
1.1.3. Search store by filters (price, category, brand, etc.) <br>
1.1.4. See products on sale via home page, under 'Sale'. <br>
1.1.5. Change personal information (name, email, password, address, etc.) <br>
1.1.6. Add a credit card. <br>

#### 1.2. Company:
1.2.1. See a list of added products. <br>
1.2.2. Change information of the product if it is on sale, etc. <br>
1.2.3. Add a bank account. <br>
1.2.4. See information of user that ordered products. 

## Realization:
### Technologies
- Angular 2+ (front-end) 
- Spring Boot (back-end) 

### Database
- PostgreSQL 

### Other
- Cloudinary (for saving product image)
- Authentication and Authorization via JWT
- Hibernate Criteria for writing queries 
- Facebook APIs for Authentication 
- Own APIs for getting new arrivals collection, saving new user, saving new product, etc
- Pagination of received collection from API
- Validation for forms

## Install and run:
After cloning git repository:
Backend:
1. Open backend/shop in IDE (I worked in Intellij IDEA)
2. Build project
3. Run application
Frontend:
1. Open frontend/app in code editor (visual studio code, etc.)
2. Install packages by entering command in terminal: npm install
3. Run frontend with command: ng serve

## General flow:
User:
1. Creating an account manually: <br>
1.1. Create an account manually by clicking on 'Create an Account' link. <br>
1.2. Enter personal information and confirm. <br>
1.3. Account is created and saved in database. <br>
1.4. Click on 'Login' link. <br>
1.5. Enter required information and confirm. <br>
1.6. User is now logged in application.

2. Login with Facebook <br>
2.1. Click on 'Login' link.
2.2. Click on button 'Login with Facebook'.
2.3. Account is created and saved in database with information from facebook.
2.4. User is now logged in application.

Company:
1. Admin login information: <br>
  1.1. email: info@cm.ba, password: 12345678 <br>
  1.2. email: info@dm.ba, password: 12345678
2. Login by clicking on 'Login' link.
3. After login, click on 'Add new item'.
4. Enter all required information of item and confirm.
5. Go back to home page and under 'New Arrivals' is new item added.


## Deployed application:
1. First open API's on link: https://shopwisely-api.herokuapp.com
2. After that, open link: https://shopwisely-app.herokuapp.com


