# Read Me First

E-commerce service:

* Descargar las fuentes del repositorio a través del comando git clone
* Importar el proyecto en el IDE
* Ejecutar el comando mvn clean install en la ruta del proyecto
* Iniciar el microservicio
* Para testear los endpoints, importar el contenido del archivo src/main/resources/Ecommerce-xcale.postman_collection.json

##Endpoints

 ### generateCart
    Servicio que genera el carito con un codigó único (uuid) con un TTL de 10 minutos (Si se desea modificar el tiempo, ir al archivo com/xcale/ecommerce/util/CaffeineConfigs.java)

 ### getProducts
    Servicio que se encarga de listar los productos disponibles (La información se obtiene a traves de una api externa a traves del cliente Feign)
 
 ### addProductsToCart
    Servicio que se encarga de agregar los productos (id, description, amount) al cart generado previamente (/generateCart). En caso no se cuente con idCart generado, se mostrará una excepción.

 ### deleteCart
    Elimina el id cart almacenado en cache