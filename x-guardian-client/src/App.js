import './App.css'
import { Dashboard } from './pages/Dashboard'
import { Alerts }  from './pages/Alerts'
import { ChakraProvider } from '@chakra-ui/react'
import { Route } from "wouter"
import Header from './components/Header'

function App() {
  return (
    <ChakraProvider>
      <Header/>
      <Route path="/dashboard" component={Dashboard} />
      <Route path="/" component={Alerts} />
    </ChakraProvider>
  )
}

export default App;
