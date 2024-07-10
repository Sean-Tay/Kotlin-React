export const setup = () => {
    // Fix JS Date Timezone here so that Unit Tests involving Date Parsing is consistent across locations.
    // process.env.TZ = "Asia/Singapore"

    // OR

    process.env.TZ = "Etc/UTC"
}