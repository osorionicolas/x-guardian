import React, { useEffect, useState } from 'react'
import { Box, Grid } from '@chakra-ui/react'

import Card from '../components/Card'
import axios from 'axios'
import { API_URL } from '../environment'

export const Alerts = () => {
    const [alerts, setAlerts] = useState([])
    const [coords, setCoords] = useState()

    const fetchData = async () => {
        try {
            axios(`${API_URL}/alert-types`).then(response => {
                const data = response.data
                console.log(data)
                setAlerts(data)
            })
        }
        catch(error){
            console.log(error)          
        }
    }

    const getLocation = () => {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const coords = (({ latitude, longitude }) => ({ latitude, longitude }))(position.coords)
                console.log(coords)
                setCoords(coords)
            },
            (error) => {
                console.error("Error Code = " + error.code + " - " + error.message)
            }
        )
    }
    
    useEffect(() => {
        fetchData()
        getLocation()
    }, [])

    return (
        <Box display='flex' height='calc(100vh - 80px)' justifyContent='center' alignItems='center' flexWrap='wrap'>
            <Grid gridTemplateColumns='repeat(auto-fit, minmax(20rem, 1fr))' w='100%' gap='4rem' p='4rem'>
                {alerts.map(alert => {
                    return <Card alert={alert} coords={coords} key={alert.id}></Card>
                })}
            </Grid>
        </Box>
    )
}