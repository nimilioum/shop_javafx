drop procedure if exists insertProduct;
create procedure insertProduct(in inName text, in inPrice double, in inCount integer,
    in inImgPath text, in inDescription text, out id integer)
begin
    insert into product(name, price, count, image_path, description) values (inName, inPrice, inCount, inImgPath,
                                                                             inDescription);
    SELECT LAST_INSERT_ID() into id;
end;

drop procedure if exists insertCategory;
create procedure insertCategory(in inName text, out id integer)
begin
    insert into category(name) values (inName);
    SELECT LAST_INSERT_ID() into id;
end;

drop procedure if exists insertProductCategory;
create procedure insertProductCategory(in productId integer, in categoryId integer)
begin
    insert into product_category(product_id, category_id) values (productId, categoryId);
end;


# ----------------------------------------------------------------------------------------


drop procedure if exists updateProductPrice;
create procedure updateProductPrice(in newPrice integer, in productId integer)
begin
    update product set price = newPrice where  id = productId;
end;

drop procedure if exists updateProductCount;
create procedure updateProductCount(in newCount integer, in productId integer)
begin
    update product set count = newCount where  id = productId;
end;

drop procedure if exists updateProductName;
create procedure updateProductName(in newName text, in productId integer)
begin
    update product set name = newName where  id = productId;
end;

drop procedure if exists updateProductImage;
create procedure updateProductImage(in newImage text, in productId integer)
begin
    update product set image_path = newImage where  id = productId;
end;

drop procedure if exists updateProductDescription;
create procedure updateProductDescription(in newDescription text, in productId integer)
begin
    update product set description = newDescription where  id = productId;
end;


drop procedure if exists updateProductSales;
create procedure updateProductSales(in productId integer, in in_count integer)
begin
    update product set sales = sales + in_count where  id = productId;
end;


#########################

# ----------------------------------------------------------------------------------------


drop procedure if exists deleteProduct;
create procedure deleteProduct(in productId integer)
begin
    delete from product where id = productId;
end;

drop procedure if exists deleteCategory;
create procedure deleteCategory(in categoryId integer)
begin
    delete from category where id = categoryId;
end;


# ----------------------------------------------------------------------------------------


drop procedure if exists getAllProducts;
create procedure getAllProducts()
begin
    select * from product order by sales, id;
end;

drop procedure if exists getMostSoldProducts;
create procedure getMostSoldProducts()
begin
    select * from product order by -sales limit 50;
end;

drop procedure if exists searchProduct;
create procedure searchProduct(in in_name text)
begin
    select * from product where name like CONCAT('%', in_name ,'%');
end;

drop procedure if exists findProduct;
create procedure findProduct(in in_name text)
begin
    select * from product where name = in_name;
end;

drop procedure if exists getProductById;
create procedure getProductById(in productId integer)
begin
    select * from product where id = productId;
end;

drop procedure if exists getProduct;
create procedure getProduct(in in_id integer)
begin
    select * from product where id = in_id;
end;

drop procedure if exists getAllCategories;
create procedure getAllCategories()
begin
    select * from category;
end;

drop procedure if exists getProductCategories;
create procedure getProductCategories(in productId integer)
begin
    select * from category join product_category pc on pc.product_id = productId;
end;



drop procedure if exists findCategory;
create procedure findCategory(in in_name text)
begin
    select * from category where name = in_name;
end;

drop procedure if exists searchCategory;
create procedure searchCategory(in in_name text)
begin
    select * from category where name like CONCAT('%', in_name ,'%');
end;


drop procedure if exists getProductsByCategory;
create procedure getProductsByCategory(in category integer)
begin
    select * from product inner join product_category pc on pc.category_id = category and  product.id = pc.product_id;
end;


drop procedure if exists searchProducts;
create procedure searchProducts(in inName text)
begin
    declare categoryId integer;
    select id from category where name = inName into categoryId;
    select searchProduct(inName) union select getProductsByCategory(categoryId);
end;
