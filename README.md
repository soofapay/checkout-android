# Soofa Integration:

![Alt text](https://github.com/soofapay/checkout-android/raw/master/sample-screenshot.jpg =320*480)


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

## Description

The result ```Soofa.TRANSACTION_SUCCESSFUL ```  means the transaction went through and the user has successfully made the payment. 

The Extra data received is the transaction id, as ```"tid"``` and the reference as ```"reference"```.  

Other responses include:
*  ``` Soofa.TRANSACTION_FAILED ```  In cases where the transaction has failed because of bad data or network issues. Check log for more details.
*  ``` Soofa.TRANSACTION_CANCELLED ```  In cases where the user has canceled the transaction.
Both cases return a ``` "message" ``` .
