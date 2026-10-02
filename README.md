# payblast-purchases-kmp

Payblast Kotlin Multiplatform purchases SDK

This repository does not vendor RevenueCat source. The public API shape is studied from [https://github.com/RevenueCat/purchases-kmp](https://github.com/RevenueCat/purchases-kmp) and reimplemented against Payblast.

## Contract

```text
configure(apiKey, appUserId)
logIn(appUserId) / logOut()
getOfferings()
purchase(package)
restore()
getCustomerInfo()
presentPaywall(offering?)
```

```bash
./gradlew test
```

iOS purchases stay in `payblast-purchases-ios`. This module is the shared HTTP API for targets that can call Payblast directly.

`getCustomerInfo` exposes `entitlements[lookupKey].isActive`. Packages carry the store product identifier for this SDK's platform. Purchases of digital goods inside the native app go through that store. Web purchases use Stripe Checkout on the app maker's connected account.

## Reference

- https://github.com/RevenueCat/purchases-kmp
