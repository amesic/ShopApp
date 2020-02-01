export function checkIfThereIsNoItemsLeft(page, size, totalNumberOfItems) {
    return totalNumberOfItems - page * size <= 0;
  }