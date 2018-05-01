## Fun with Ant Design
We've been rather curious about Ant Design and more than impressed with the rapid evolution in its codebase. Initial experience
with the Ant Design Pro template revealed well designed, reusable patterns and a nice, modern UX.

### What's Needed from You
- Use Ant Design Pro CLI tool to scaffold a new project called `Ringmaster`
- Add functionality in the scaffolded app to list and manage employees (see Employee Model below)
- Provide your solution either as a zip file (please do not include `node_modules`) or a link to an online repository

### A successful solution
- displays a menu item called `Employees` that routes to the Employee Listing page
- allows the user to create, update or delete an employee
- mocks all endpoints for listing and managing an employee
- respects the `Employee` data model
- introduces a dva model to encapsulate reducers and effects
- works

### Employee Data Model
Our employee data model is described by the following JSON:
```json
{
  "firstName": "string"
  "lastName": "string"
  "dob": "date-only",
  "department": "enum [FRONT,BACK,MIDDLE]",
  "hobbies": "list<string>"
}
```
Note that `firstName`, `lastName` and `department` are required and `dob` must be on or before current day.


### Helpful Pointers
- [Roadhog Mocking](https://github.com/ant-design/ant-design-pro/blob/master/.roadhogrc.mock.js)
- [Menu](https://github.com/ant-design/ant-design-pro/blob/master/src/common/menu.js)
- [Router](https://github.com/ant-design/ant-design-pro/blob/master/src/common/router.js)
- [Model](https://github.com/ant-design/ant-design-pro/tree/master/src/models)

### References
- [Ant Design](https://ant.design/)
- [Ant Design - Overview](https://ant.design/docs/spec/introduce)
- [Ant Design Pro](https://pro.ant.design/)
- [Ant Design Pro - Getting Started](https://pro.ant.design/docs/getting-started)
- [Ant Design Pro - GitHub](https://github.com/ant-design/ant-design-pro)
