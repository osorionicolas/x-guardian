import {
  Box, Button, Icon
} from "@chakra-ui/react"
import axios from 'axios'
import { API_URL } from '../environment'
import { MdOutlinePets } from 'react-icons/md'
import { GiRobber } from 'react-icons/gi'
import { RiFloodFill } from 'react-icons/ri'
import { BsLightbulbOffFill } from 'react-icons/bs'

export default function Card({alert, coords}) {
  const { id, name, icon } = alert

  const createAlert = (alert) => {
    axios.post(`${API_URL}/alerts`, {
      type: {
        id: alert.id,
        name: alert.name,
      },
      user_id: 1,
      location: coords,
      created_at: new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '')
    })
  }

  const capitalize = (s) => s.charAt(0).toUpperCase() + s.slice(1)

  return (
    <Box maxW='sm' borderWidth='1px' borderRadius='lg' display='flex' alignItems='center' flexDirection='column' padding='42px 54px 32px 54px'>
      <Icon as={icon} boxSize={28}/>

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