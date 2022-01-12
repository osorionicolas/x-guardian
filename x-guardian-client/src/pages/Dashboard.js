import { Box } from '@chakra-ui/react'
import React from 'react'
import Chart from '../components/Chart'

export const Dashboard = () => {
    return (
        <Box display='flex' height='calc(100vh - 80px)' alignItems='center' justifyContent='space-evenly' flexWrap="wrap">
            <Chart height={'400px'} width={'400px'} chartId={'4d76985d-5c63-447b-bfb2-262236afbaeb'}/>
            <Chart height={'400px'} width={'400px'} chartId={'97765446-c238-484a-ae65-6625b9f9fc80'}/>
        </Box>
    )
}
