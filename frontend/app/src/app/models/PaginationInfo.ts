import { Product } from "../models/Product";

export class PaginationInfo<type> {
  pageSize;
  pageNumber;
  totalNumberOfItems;
  items: type[];
}
