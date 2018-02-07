# ![steeleye-logo](https://avatars1.githubusercontent.com/u/25005358?s=60&v=4) SteelEye 

## Platform Developers Test

Thank you for your interest in the platform developer role at [SteelEye](https://steel-eye.com).

We hope the test will not only test your abilities but also give you a little glimpse into some of the tasks we carry out. We expect you to read as much code as you would write and hence this assignment is provided as an incomplete project rather than something you'd start from scratch. In fact the code is a partial extract of our real-life code base and this is the kind of challenges you'd be expected to solve everyday.

You are welcome to use any resources that would be available to you during the course of a normal day: e.g. Google is permitted, as are textbooks, however use of existing code libraries you have developed anywhere else would not be allowed. You may include code taken from the internet including JARs that are freely downloadable and have no restrictions on usage. In other words, they must be licensed for commercial use without restriction. You should clearly identify and attribute sources within your code to their appropriate ownership.

One final word of warning - at a startup like SteelEye you will regularly gather your own requirements directly or receive vague details by email. Sometimes it is not possible to get a very detailed specification simply because the user doesn’t know or isn’t available. When this happens you have to use your initiative and work things out for yourself. This test has several ambiguities and vague definitions. It is up to you to make reasonable and valid assumptions. Do not ask your recruitment consultant for help or advice as they know no more than you do.

And one final tip: KISS – Keep It Short and Simple. Don’t overcomplicate it, make it simple, efficient, easy to maintain and easy for the user to use.

### Introduction to the problem

As part of the regulatory obligations within SteelEye, we constantly work with specifications from different governing bodies including [ESMA](https://www.esma.europa.eu/). And implement different specifications and convert them into workable code. As part of this coding exercise, we would like you to implement a small routine that has been defined in ESMA's MiFID II transaction reporting guideline <https://www.esma.europa.eu/sites/default/files/library/2016-1452_guidelines_mifid_ii_transaction_reporting.pdf>. 

**CONCAT CODE** is a specific concatenation of certain fields within a application user model and is defined in pages 24-27 of the specification. Write a thread safe routine that could be called to generate the CONCAT code for any user object received by your application.

#### Code Organization

* Based on the problem statement above, the project is organized as a maven project with proper unit testing.
* All the tests are valid and work as expected - so you're advised not to modify the existing tests but more than welcome to add extra ones where you feel necessary.
* Three methods have been purposely omitted and are commented as //TODO for you to implement and fix the failing tests. 
   * DataParser#parseDate - since we collect data from multiple sources and formats, dates could come in any format and this method normalises the input.
   * DeepRetriever#deepCollect - as a generic platform, we don't deal with POJOs and data is always treated as a generic map and hence map operations are at the heart of all our platform code and hence we expect you to understand the requirements from the unit tests and implement an elegant utility.
   * ConcatIdBuilder#buildConcatCode - the main logic and algorithm we would like you to implement. The explanation for this is provided in the website above.
* You're expected to fix any other bug that might be found in the main code.

### How to submit your work

You can submit your solution as a zip file along with all necessary source and test code or can be committed to a private repository in GitHub or BitBucket or a private GIST in Github. 

Please do not share your solution or make it open source. We would like other candidates to think and try for themselves rather than copying your code and replacing variable names.

Many thanks for taking your time to complete the test. Wish you all the best.