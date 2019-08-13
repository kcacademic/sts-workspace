http://localhost:9001/graphiql

query {
  recentCustomers(count: 10, offset: 0) {
    id
    firstName
    lastName
  }
}

mutation {
  writeCustomer(firstName:"Kumar", lastName: "Chandrakant"){
    id
  }
}

subscription {
  getNewCustomer {
    firstName
    lastName
  }
}
