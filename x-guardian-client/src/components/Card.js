import { Box, Button, Icon, useToast } from "@chakra-ui/react"
import axios from 'axios'
import { API_URL } from '../environment'
import * as icons from './react-icons-proxy.js'

export default function Card({alert, coords}) {
  const { id, name, icon } = alert
  const toast = useToast()

  const createAlert = () => {
    axios.post(`${API_URL}/alerts`, {
      alert: {
        id: id,
        name: name,
      },
      user_id: "1",
      location: coords,
      created_at: new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '')
    }).then(res => {
      let message = 'Alert reported.'
      let status = 'success'
      if(res.status !== 201){
        message = 'Something went wrong.'
        status = 'error'
      }

      toast({
        title: message,
        status: status,
        duration: 3000,
        isClosable: true,
      })
    })
  }

  const capitalize = (s) => s.charAt(0).toUpperCase() + s.slice(1)

  return (
    <Box maxW='sm' borderWidth='1px' borderRadius='lg' display='flex' alignItems='center' flexDirection='column' padding='36px 36px 32px 36px'>
      <Icon as={icons[icon]} boxSize={28}/>

      <Box pt='6' pb='12'>
        <Box
          fontWeight='semibold'
          as='h4'
        >
          {capitalize(name)}
        </Box>
      </Box>
      <Box>
        <Button 
          colorScheme='red'
          w='36'
          onClick={createAlert}
          isDisabled={coords === undefined}
        >
          Report
        </Button>
      </Box>
    </Box>
  )
}