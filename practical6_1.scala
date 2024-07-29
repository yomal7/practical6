def getProductNames(map: Map[String,Map[String,Any]], list: List[String]): List[String] = {
  if(map.isEmpty) list
  else{
    val newList = list :+ map.head._2("name").toString
    getProductNames(map.tail, newList)
  }
}

def calcTotalValue(map: Map[String, Map[String, Any]]): Int = {
  if (map.isEmpty) 0
  else {
    val product = map.head._2
    val price = product("price").toString.toInt
    val quantity = product("quantity").toString.toInt
    price * quantity + calcTotalValue(map.tail)
  }
}

def isInventoryEmpty[K, V](map: Map[K, V]): Unit = {
  if (map.isEmpty) println("\nInventory is Empty.")
  else println("\nInventory isn't Empty.")
}

def mergeInventory(map1: Map[String, Map[String, Any]], map2: Map[String, Map[String, Any]]): Map[String, Map[String, Any]] = {
  if(map1.isEmpty) map2
  else if(map2.isEmpty) map1
  else{
    val(key, product1) = map1.head
    val mergeProduct = map2.get(key) match{
      case Some(product2) =>
        Map(
          "Id" -> product1("Id"),
          "name" -> product1("name"),
          "price" -> math.max(product1("price").toString.toInt, product2("price").toString.toInt),
          "quantity" -> (product1("quantity").toString.toInt + product2("quantity").toString.toInt)
        )
      case None => product1
    }
    Map(key -> mergeProduct) ++ mergeInventory(map1.tail, map2- key)
  }
}

def checkbyId(map: Map[String, Map[String,Any]], Id: String): Unit = {
  val foundProduct = map.values.find(_("Id") == Id)

  foundProduct match {
    case Some(product) =>
      println(s"\nProduct with Id $Id found")
      println(s"Name: ${product("name")}, Price: ${product("price")}, Quantity: ${product("quantity")}")
    case None => println(s"\nProduct with Id $Id not found.")
  }
}

@main def practical6_1(): Unit = {
  val product1 = Map("Id" -> "01", "name" -> "Milk", "price" -> 50, "quantity" -> 20)
  val product2 = Map("Id" -> "02", "name" -> "Chocolate", "price" -> 100, "quantity" -> 45)
  val product3 = Map("Id" -> "03", "name" -> "Coconut", "price" -> 65, "quantity" -> 38)
  val product4 = Map("Id" -> "01", "name" -> "Milk", "price" -> 50, "quantity" -> 25)
  val product5 = Map("Id" -> "02", "name" -> "Chocolate", "price" -> 130, "quantity" -> 40)
  val product6 = Map("Id" -> "03", "name" -> "Coconut", "price" -> 70, "quantity" -> 30)
  val product7 = Map("Id" -> "04", "name" -> "Biscuit", "price" -> 35, "quantity" -> 55)
  
  val inventory1 = Map(
    "product1" -> product1,
    "product2" -> product2,
    "product3" -> product3
  )

  val inventory2 = Map(
    "product1" -> product4,
    "product2" -> product5,
    "product3" -> product6,
    "product4" -> product7
  )
  
  var list1 = getProductNames(inventory1, List())
  println("Produnts names of inventory 01 :")
  list1.foreach(println)

  val totalvalue = calcTotalValue(inventory1)
  println(s"\nTotal value of all products in inventory1: ${totalvalue}")

  isInventoryEmpty(inventory2)

  val mergedInventory = mergeInventory(inventory1, inventory2)
  println("\nMerged Invetory: ")
  mergedInventory.foreach {case(key, product) =>
    println(s"$key : ${product("name")}, Price: ${product("price")}, Quantity: ${product("quantity")}")
    }

  checkbyId(inventory1, "02")

}