import { Brand } from './Brand';
import { Category } from './Category';
import { Company } from './Company';

export class Product {
    id;
    date_published;
    description;
    image;
    name;
    price;
    brand: Brand;
    category: Category;
    subcategory: Category;
    company: Company;
}