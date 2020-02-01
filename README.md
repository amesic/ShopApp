# Shop Wisely Application

Shop Wisely is an application for cosmetic companies in Bosnia and Herzegovina (CM, DM, etc), that saves users time going to the different markets looking for the best prices for them selves. Users of this application search and compare prices between same products, but different companies. 

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

### 2. Features that needs to be provided:
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
1. Admin login information:
1.1. email: info@cm.ba, password: 12345678 <br>
1.2. email: info@dm.ba, password: 12345678


## Deployed application:
1. https://shopwisely-app.herokuapp.com
2. https://shopwisely-api.herokuapp.com


