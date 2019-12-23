# Technical Test

This task should demonstrate Infrastructure engineering skills, using all of the below:

- AWS services
- Infra as a Code(IAC) tools such as AWS Cloud Formation,Terraform etc.,
- End to End Automation
- Configuration Management (or) Scripting
- Source Control

If your solution provides a list of manual steps, you are doing it wrong.

### Task

a) Launch 3 separate Linux nodes using the tools of your choice

    2 x application nodes
    1 x web node

b) Deploy the sample application to the appropriate nodes

c) Install NGINX on the web node and balance requests in a round-robin fashion

### Goal

Sending a HTTP request to the web node should return the response:  
`Hi there, I'm served from <application node hostname>!`

Demonstrate the round-robin mechanism is working correctly

### Considerations

    Share your work on a public git repo
    Include a README.md with clear and concise instructions
    Invocation should be simple and intuitive
    Take care not to over engineer a solution

---

### Sample application code (Golang)
```
package main

import (
	"fmt"
	"net/http"
	"os"
)

func handler(w http.ResponseWriter, r *http.Request) {
	h, _ := os.Hostname()
	fmt.Fprintf(w, "Hi there, I'm served from %s!", h)
}

func main() {
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8484", nil)
}
```
