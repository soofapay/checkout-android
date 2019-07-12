# Soofa Integration:

Initiating soofa client

## Usage

```java
new Soofa(this,
     5002,
     amount,
     domain,
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

            switch (resultCode){
                case Soofa.TRANSACTION_FAILED:
                    /**
                     *
                     * The transaction did not go through.
                     * Network issues, bad data et al.
                     *
                    *  In case the transaction failed, the extra data is  {"message"}
                     *
                     *  Use your logic here
                    * */

                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    break;

               case Soofa.TRANSACTION_CANCELLED:

                   /**
                    *The user has cancelled the transaction, onBackPressed or the transaction timed out
                    *
                    * Same response as  {@link  Soofa.TRANSACTION_FAILED}
                    *
                    *
                    * input your logic here.
                    *
                    *
                    * */
                   Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                   Log.e(TAG, message );
                   break;

                case Soofa.TRANSACTION_SUCCESSFUL:
                    /**
                     *The transaction is successful.
                     *
                     * Response includes the transaction id    {"tid"}
                     * and the reference {"reference"}
                     *
                     * input your logic here
                     * **/
                    String tId = data.getStringExtra("tid");
                    String reference = data.getStringExtra("reference");
                    Log.e(TAG, "onActivityResult: " + tId + " " + reference );
                    break;
            }
        }

    }
```
