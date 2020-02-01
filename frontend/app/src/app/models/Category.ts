import { Brand } from './Brand';

export class Category {
    id;
    name;
    subcategory: Category[];
    brand: Brand;
}