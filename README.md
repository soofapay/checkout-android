# Soofa Integration:

![](https://github.com/soofapay/checkout-android/raw/master/sample-screenshot.jpg)

Add Jitpack to your project-level build.gradle file

```allprojects 
{
	...
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
  ```

## Library dependency

To add the library, paste this line to your app-level build.gradle file.

```implementation 'com.qubeans.checkout:checkout:1.0.10'```


Initiating soofa client

## Usage

### java

```java
new Soofa(this, yourTillNumber, amount, reference).create();
```

### Kotlin

```kotlin
Soofa(this, yourTillNumber, amount, reference).create();
```

`yourTillNumber` - The business name, of type Double.

`this` - the current Activity. Use `getActivity()` inside a fragment. 

`amount` - amount for product, of Double type.


  ## Usage
  ### java
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
		  // Submit to your server
          }
      }

  }
```
### kotlin
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Soofa.TRANSACTION_REQUEST_CODE && data != null){
            if (resultCode == Soofa.TRANSACTION_SUCCESSFUL){
                var tId = data.getStringExtra("tid")
                var reference = data.getStringArrayExtra("reference")
                Logger.getLogger("Response").warning("$tId $reference")
		// Submit to your server
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
