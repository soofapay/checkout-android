# Soofa Integration:

Initiating soofa client

## Usage

```java
new Soofa(this,
          5002,
          amount,
          reference)
  .create();

```

5002 - The business name, of type Double.

This - the current Activity. 

Amount - amount for product, of Double type.

Domain -  the domain of the organization. To be added on allowed hosts from the dashboard.


  ## Usage
  ```java
@Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      if (requestCode == Soofa.TRANSACTION_REQUEST_CODE && data != null) {
          String message = data.getStringExtra("message");

          if (resultCode==Soofa.TRANSACTION_SUCCESSFUL){
            
                  String tId = data.getStringExtra("tid");
                  String reference = data.getStringExtra("reference");
                  Log.e(TAG, "onActivityResult: " + tId + " " + reference );
          }
      }

  }
```
