import {
    Box, Button,
} from "@chakra-ui/react"

export default function Card({icon, title}) {
    return (
      <Box maxW='sm' borderWidth='1px' borderRadius='lg' display='flex' alignItems='center' flexDirection='column' padding='42px 54px 32px 54px'>
        {icon}
  
        <Box pt='6' pb='12'>
          <Box
            fontWeight='semibold'
            as='h4'
          >
            {title}
          </Box>
        </Box>
        <Box>
          <Button colorScheme='red' w='36'>Report</Button>
        </Box>
      </Box>
    )
  }