import React from 'react'
import { Bar } from 'react-chartjs-2';
import { Grid } from '@chakra-ui/react'

export default function Leaderboard({scores}) {
  return (
    <Grid>
      <Bar
        width={20}
          height={5}
          data={{
            labels: scores[1],
            datasets: [{ 
              label: 'Seconds',
              data: scores[0],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
              ],
              borderWidth: 1,
            }]
          }}
          options={{
            indexAxis: 'y',
            elements: {
              bar: { borderWidth: 2 },
            },
            plugins: {
              legend: { display: false, },
              title: {
                display: true,
                text: 'Watched seconds per student',
              },
            },
            scales: {
              y: { max: 4 }
            }
          }}
      />
  </Grid>
  )
}