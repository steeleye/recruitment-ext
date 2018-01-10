# ![steeleye-logo](https://avatars1.githubusercontent.com/u/25005358?s=60&v=4) SteelEye 

## Platform Developers Test

Thank you for your interest in the platform developer role at [SteelEye](https://steel-eye.com).

We hope the test will not only test your abilities but also give you a little glimpse into some of the tasks we carry out. You are welcome to use any resources that would be available to you during the course of a normal day: e.g. Google is permitted, as are textbooks, however use of existing code libraries you have developed anywhere else would not be allowed. You may include code taken from the internet including JARs that are freely downloadable and have no restrictions on usage. In other words, they must be licensed for commercial use without restriction. You should clearly identify and attribute sources within your code to their appropriate ownership.

One final word of warning - at a startup like SteelEye you will regularly gather your own requirements directly or receive vague details by email. Sometimes it is not possible to get a very detailed specification simply because the user doesn’t know or isn’t available. When this happens you have to use your initiative and work things out for yourself. This test has several ambiguities and vague definitions. It is up to you to make reasonable and valid assumptions. Do not ask your recruitment consultant for help or advice as they know no more than you do.

And one final tip: KISS – Keep It Short and Simple. Don’t overcomplicate it, make it simple, efficient, easy to maintain and easy for the user to use.

### Introduction to the problem

As part of the regulatory obligations within SteelEye, we constantly work with specifications from different governing bodies including [ESMA](https://www.esma.europa.eu/). And implement different specifications and convert them into workable code. As part of this coding exercise, we would like you to implement a small routine that has been defined in ESMA's MiFID II transaction reporting guideline <https://www.esma.europa.eu/sites/default/files/library/2016-1452_guidelines_mifid_ii_transaction_reporting.pdf>. 

**CONCAT CODE** is a specific concatenation of certain fields within a application user model and is defined in pages 24-27 of the specification. Write a thread safe routine that could be called to generate the CONCAT code for any user object received by your application.

#### Assumptions

* A user object is received as a JSON document with any number of arbitrary field
* A user object may or may not contain all the necessary fields required to generate a CONCAT code.
* If the user object contains all the fields necessary for the code, the code should be generated and returned. Else a null code may be returned.
* Should adhere all constraints posted by the specification.
* Ensure that your code is thread-safe and can potentially handle a large number of invocations.
* Please support your code with additional unit-tests to ensure thread safety and other design constraints that you have employed in your solution.

#### Sample data for testing

| First Name | Surname |  DOB  | Nationality - 1 | Nationality - 2 | Nationality-3 | Expected CONCAT |
| ---------- | ------- | :---: | :-------------: | :-------------: | :-----------: | --------------- |
| Åke | Öjvind | 07/07/1959 | FI | -- | --| FI19590707AKE##OJVIN |
| Jan | Van De Merwe | 12/12/1960 | NL | -- | -- | NL19601212JAN##MERWE |
| Äärnästi | Friðjóna | 16/02/1975 | IS | -- | -- | IS19750216AARNAFRIDJ |
| Hlégestur | Norðmaðr | 16/03/1978 | SE | -- | -- | SE19780316HLEGENORDM |
| Mary-Jane | Montana | 25/12/1975 | GB | FR | CA | CA19751225MARYJMONTA |
| ALŽBĚTA | BLAŽEJ | 03/01/1957 | CZ | -- | --| CZ19570103ALZBEBLAZE |
| ANDŻELIKA | BOGUSŁAWA | 12/04/1965 | HU | PL | -- | HU19650412ANDZEBOGUS |
| ŻAKLINA | SZCZĘSNY | 31/10/1969 | FR | ES | -- | ES19691031ZAKLISZCZE |
| Альберт | Беломестных | 06/05/1972 | GB | -- | -- | GB19720506ALBIEBIELO |
| Ευριπιδης | Θεμιστοκλης | 11/11/1983 | GR | -- | -- | GR19831111EURIPTHEMI |


### How to submit your work

You can submit your solution as a zip file along with all necessary source and test code or can be committed to a private repository in GitHub or BitBucket or a private GIST in Github. 

Please do not share your solution or make it open source. We would like other candidates to think and try for themselves rather than copying your code and replacing variable names.

Many thanks for taking your time to complete the test.
