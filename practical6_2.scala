

@main def practical6_1(): Unit = {
    product1 = Map("Id"-> "01","name"->"Milk", "price"->50, "quantity"->20)
    product2 = Map("Id"-> "02","name"->"Chocolate", "price"->100, "quantity"->45)
    product3 = Map("Id"-> "03","name"->"Coconut", "price"->65, "quantity"->38)

    inventory = Map("product1"->"product1", "product2"->"product2", "product3"->"product3")

    println(inventory("product1("Id")"))
}