from mysql import connector
import sys

user = sys.argv[1]
password = sys.argv[2]
database = sys.argv[3]


def run(name):
    with open(f'sql/{name}.sql', 'r') as f:
        with mydb.cursor() as cursor:
            query = f.read()
            result = cursor.execute(query, multi=True)
            for r in result:
                _ = r.statement
    mydb.commit()


mydb = connector.connect(
    host="localhost",
    user=user,
    passwd=password,
    database=database,
)

files = ["customer", "shopStaff", "inventoryStaff", "deliveryStaff", "product_category", "order_item"]

for file in files:
    run(file)
