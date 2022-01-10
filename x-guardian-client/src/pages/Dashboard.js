import { Box } from '@chakra-ui/react'
import React from 'react'
import Chart from '../components/Chart'

export const Dashboard = () => {
    return (
        <Box display='flex' justifyContent='space-evenly'>
            <Chart height={'400px'} width={'400px'} chartId={'4d76985d-5c63-447b-bfb2-262236afbaeb'}/>
            <Chart height={'400px'} width={'400px'} chartId={'9b989615-5a20-4814-9dc9-814e6c99ddf5'}/>
        </Box>
    )
}
